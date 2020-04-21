# CollegeHelpers
## BottomNavigation
`labelVisibilityMode` 标签在未点击时是否可见

`itemIconTint` 图标颜色	`itemTextColor` 标签颜色	由选择器进行适配

`menu` 按钮的布局文件

---

需要添加依赖

```
implementation 'com.google.android.material:material:1.0.0'
implementation 'com.android.support:design:27.1.1'
```

---

其他说明（踩过的坑）

1. drawable文件夹中的xml文件为向量格式，可以通过SVG图片定义其中的PathData，但不可以更改颜色。mipmap中的png文件可以在选择器中修改颜色
2. 选择器需要新建color文件夹
3. main文件里的函数为其需要连接fragment时候的函数，可以根据注释适当使用
