package com.fragmentedchaos.safespawn;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Mod configuration. Loads automatically from config/safespawn.properties
 * when any static field is accessed for the first time.
 * No loader API dependency — Mixin references trigger initialization.
 */
public final class SafeSpawnConfig {

    private static final Path CONFIG_PATH = Path.of("config", "safespawn.properties");

    public static int invulnerableTicks = 60;
    public static boolean enableRespawnImmunity = true;
    public static boolean enableLoginImmunity = true;

    static {
        load();
    }

    private static void load() {
        Properties props = new Properties();
        Path path = CONFIG_PATH;

        if (Files.exists(path)) {
            try (InputStream in = Files.newInputStream(path)) {
                props.load(new java.io.InputStreamReader(in, StandardCharsets.UTF_8));
            } catch (IOException e) {
                System.err.println("[SafeSpawn] Failed to load config: " + e);
            }
        } else {
            // Create default config if missing
            try {
                Files.createDirectories(path.getParent());
                String content = "# SafeSpawn Config\n"
                        + "# invulnerableTicks: immunity duration in game ticks (20 = 1 second)\n"
                        + "invulnerableTicks=60\n"
                        + "# enableRespawnImmunity: grant immunity after death respawn\n"
                        + "enableRespawnImmunity=true\n"
                        + "# enableLoginImmunity: grant immunity on login\n"
                        + "enableLoginImmunity=true\n";
                Files.writeString(path, content, StandardCharsets.UTF_8);
                System.out.println("[SafeSpawn] Created default config: " + path.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("[SafeSpawn] Failed to create config: " + e);
            }
        }

        invulnerableTicks = parseInt(props, "invulnerableTicks", 60);
        enableRespawnImmunity = parseBool(props, "enableRespawnImmunity", true);
        enableLoginImmunity = parseBool(props, "enableLoginImmunity", true);
    }

    private static int parseInt(Properties p, String key, int def) {
        try { return Integer.parseInt(p.getProperty(key, String.valueOf(def)).trim()); }
        catch (NumberFormatException e) { return def; }
    }

    private static boolean parseBool(Properties p, String key, boolean def) {
        return Boolean.parseBoolean(p.getProperty(key, String.valueOf(def)).trim());
    }
}
