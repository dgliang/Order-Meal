# Order Meal 🍴

**Order Meal** 是一个基于 Android 平台的点餐下单应用，旨在为用户提供离线环境下的点餐和订单管理功能。该应用在 **Android Studio** 中使用 **Java** 语言开发，采用 **RoomDatabase** 作为本地数据库存储数据。

## 功能特点 ✨

1. **用户登录与注册**  
   - 用户可通过登录菜单访问应用。
   - 新用户可通过注册菜单创建账户。

2. **菜单管理**  
   - **Main Menu** 提供食物和饮品的列表。
   - 菜单显示包括食品名称、价格和分类。

3. **下单功能**  
   - 用户可通过 **Order Menu** 选择菜品并完成下单。
   - 支持多个菜品的组合下单功能。

4. **订单历史**  
   - 用户可以通过 **Order History Menu** 查看所有历史订单，包括下单时间和总价。

5. **离线支持**  
   - 数据存储在本地的 **RoomDatabase**，无需互联网即可运行。

---

## 技术栈 🛠️

- **编程语言**: Java  
- **数据库**: RoomDatabase (用于本地数据存储)  
- **开发工具**: Android Studio
- **Android 版本**: Android 13 (API Level 33)，支持 Android 8.1 及以上版本

---

## 应用界面结构 🗂️

1. **Login Activity**  
   - 用户通过账号密码登录应用。

2. **Register Activity**  
   - 用户注册新账号，注册成功后自动跳转到登录页面。

3. **Main Activity**  
   - 查看所有可用的食物和饮品。

4. **Order Activity**  
   - 选择食物并添加到订单列表。
   - 显示订单总价并支持确认下单。

5. **History Order Activity**  
   - 查看已下单的历史记录。
   - 每个订单包含下单时间和总金额。

---

## 安装与运行 💻

1. 克隆项目到本地：  
   ```bash
   git clone https://github.com/dgliang/Order-Meal.git
   ```
2. 使用 **Android Studio** 打开项目。
3. 连接 Android 模拟器或真实设备。
4. 点击运行按钮编译并安装到设备。

---

## 数据库设计 📂

**RoomDatabase** 管理以下表：  
1. **用户表 (User Table)** ：存储用户登录信息。

---

## 项目目录结构 📁

```
OrderMeal/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/ordermeal/
│   │   │   │   ├── database/   # RoomDatabase 相关代码
│   │   │   │   ├── history/    # 订单历史相关类
│   │   │   │   ├── login/      # 登录相关类
│   │   │   │   ├── main/       # 主菜单相关类
│   │   │   │   ├── order/      # 下单相关类
│   │   │   │   ├── register/   #  注册相关类
│   │   │   │   └── utils/      # 工具类
│   │   │   ├── res/            # XML 资源文件
│   │   │   ├── AndroidManifest.xml
├── build.gradle                # 项目配置
```

---

## 未来改进计划 🚀

- 数据库中增加菜单表存储菜单项；增加订单表存储用户下单记录，包括订单时间和金额，取消硬编码模式。
- 实现菜单的动态更新功能。
- 优化用户界面，提高交互体验。
- 增加菜单菜品的筛选和搜索功能。

---

## 贡献方式 🤝

欢迎贡献本项目！请按照以下步骤提交您的贡献：  
1. Fork 本仓库。  
2. 创建一个新分支：`git checkout -b feature/your-feature-name`。  
3. 提交更改：`git commit -m 'Add some feature'`。  
4. 推送到分支：`git push origin feature/your-feature-name`。  
5. 创建一个 Pull Request。

---

## 声明：基于 YouTube 教程 📺

这个项目是参考了 YouTube 上的一篇教程开发的，教程中提供了完整的源代码和开发步骤。你可以在以下链接找到该教程以及 GitHub 上的开源代码：

- **YouTube 教程链接**: [Tutorial Membuat Aplikasi Catering dengan Android Studio](https://www.youtube.com/watch?v=YpGac1bY2Tw)  
- **GitHub 开源代码**: [Catering](https://github.com/AzharRivaldi/Catering.git)

