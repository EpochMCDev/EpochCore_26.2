# Epoch-Core

> Epoch MC 服务器服务端核心 · **基于 [Leaf](https://github.com/Winds-Studio/Leaf)**（高性能 [Paper](https://papermc.io/) fork）定制
> A high-performance Minecraft server core. **Based on [Leaf](https://github.com/Winds-Studio/Leaf).**

Epoch-Core 是面向 Epoch MC 国战服务器定制的服务端核心，基于 Leaf 26.2（Paper fork）二次开发，
继承了 Leaf / Paper / Purpur 的高性能优化、异步机制与插件兼容性，并进行了品牌化与游戏机制定制。

English | [中文](public/readme/README_CN.md)

## Features / 特性

- **Based on [Leaf](https://github.com/Winds-Studio/Leaf)**（高性能 Paper fork，Minecraft 26.2）
- **Fully compatible** with Spigot / Paper / Purpur plugins
- **Async** pathfinding, mob spawning and entity tracker
- **品牌化**：F3 服务器名、GUI 窗口标题、控制台日志前缀统一为 `EpochCore`
- **移除内置版本检查**：`/version` 不再联网查询更新
- **游戏机制定制**（`config/leaf-global.yml` → `gameplay-mechanisms.enchantment-restrictions`）：
  - 禁止从任何途径获取经验修补（Mending）附魔书
  - 禁止从任何途径获取无限（Infinity）附魔书
  - 禁止钓鱼获得附魔书
  - 移除附魔台合成配方（创造模式 / `/give` 获取的附魔台可正常使用）

## Building / 构建

构建可分发 Paperclip JAR：

```bash
./gradlew applyAllPatches && ./gradlew createPaperclipJar
```

需要 JDK 25，且必须是完整的 git clone（不能是 zip 解压）。

## Credits

- [Leaf](https://github.com/Winds-Studio/Leaf) — 本项目的基础
- [Paper](https://papermc.io/) / [Paperweight](https://github.com/PaperMC/paperweight)
- [Purpur](https://github.com/PurpurMC/Purpur) / [Gale](https://galemc.org) / [Pufferfish](https://github.com/pufferfish-gg/Pufferfish) — 部分优化与特性的来源

## License / 许可

详见 [LICENSE.md](LICENSE.md)。本项目基于 Leaf 修改，遵循原项目的开源许可（补丁多为 MIT，二进制为 GPL-3.0-only，具体以各文件头部标注为准）。
