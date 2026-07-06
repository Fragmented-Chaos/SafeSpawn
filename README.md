
# SafeSpawn

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.4--1.21.11-brightgreen)](https://www.minecraft.net/)
[![Fabric](https://img.shields.io/badge/Fabric-0.16.9-blue)](https://fabricmc.net/)
[![Quilt](https://img.shields.io/badge/Quilt-Compatible-yellow)](https://quiltmc.org/)
[![Modrinth](https://img.shields.io/badge/Modrinth-1bd96a?logo=modrinth)](https://modrinth.com/mod/safespawn)


## ✨ 功能

- **还原 MC-212 / MC-21650** – 重进存档可豁免坠落伤害（包括高处坠落时重进存档免死）
- **修复 MC-278261** – 重生无无敌问题

## ⚙️ 配置

配置文件位于 `.minecraft/config/safespawn.properties`，支持自定义：

```properties
# 无敌持续时间（游戏刻，20刻=1秒），默认 60 刻 = 3 秒
invulnerableTicks=60
# 是否在死亡重生时启用无敌（true/false）
enableRespawnImmunity=true
# 是否在登录时启用无敌（true/false）
enableLoginImmunity=true
```
