# TabooLib SDK (multi-module)

> 多模块 `TabooLib` 项目模板（Gradle `build-logic` + 两模块交付）

## 项目结构

```text
taboolib-multi-module-sdk
├── build-logic/               -- 约定插件（convention plugins）
├── build.gradle.kts           -- 根：统一 group / version
├── settings.gradle.kts        -- pluginManagement + 模块声明
├── gradle.properties
├── core/                      -- 库模块：业务逻辑、配置、对外 API
└── runtime-bukkit/            -- 交付模块：Bukkit 入口 + 合并 core 的最终插件 jar
```

## 约定插件

| ID | 应用模块 | 作用 |
| --- | --- | --- |
| `conventions.taboolib-library` | `core` | Kotlin、TabooLib env、Java 8、`taboolib.subproject` |
| `conventions.taboolib-runtime-bukkit` | `runtime-bukkit` | 继承库约定 + `implementation(:core)` + 以根项目名产出插件 jar |

## 模块职责

| 模块 | 职责 |
| --- | --- |
| `core` | 平台无关（或可选 Bukkit）业务；被 `runtime-bukkit` 打包进最终 jar |
| `runtime-bukkit` | `@PlatformSide(Platform.BUKKIT)` 启动类；**服务器使用的 jar 由此模块构建** |

## 构建发行版本

不含 TabooLib 本体，用于服务器部署。

```bash
./gradlew :runtime-bukkit:jar
```

产物：`runtime-bukkit/build/libs/ExampleProject.jar`（与 `rootProject.name` 一致）。

## 构建开发版本

含 TabooLib 本体，供二次开发，不可直接运行。

```bash
./gradlew taboolibBuildApi -PDeleteCode
```

`-PDeleteCode` 会移除逻辑代码以减小体积。

## 扩展模块

新增库模块（如 `feature-xxx`）时：

1. `settings.gradle.kts` 中 `include("feature-xxx")`
2. 模块应用 `conventions.taboolib-library` 并设置 `taboolib { subproject = true }`
3. 在 `TaboolibRuntimeBukkitConventionPlugin` 中把该模块加入 jar 合并列表（或改为可配置的 `pluginBundle` 列表）

保持 **只有一个交付模块**（`runtime-bukkit`），避免再增加空的 `plugin` 聚合壳。