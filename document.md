# 第一章 View层

## 1.1 学生端

### 1.1.1 classfeedback.html

该页面收集学生课后反馈情况

- checkState()

  检查当前学生用户是否有权限查看当前页面

- initQuestion()

  初始化试卷列表，采用随机数进行生成，同时能够保证每种题型总数量大于等于1时，该种题型一定会被选择，避免出现学生反馈界面出现0道题的bug

- getAdd()

  从0和1当中随机选取一个数字

- hashCode(str)

  产生str的hash值

- initOption(qNo,qType)

  初始化当前题目的选项，初始化qNo为试题编号，qType为试题类型

  qType类型解释：

  1：单选题

  2：多选题

  3：填空题

  4：判断题

  5：主观题

- initSaveBtn()

  初始化保存答案按钮

- initHandInBtn()

  初始化提交按钮

- initQCard()

  初始化答题卡

- initClick0()

  初始化选项点击特效

- initHasDone()

  初始化已完成题目列表

- saveAnswer()

  对当前题目所做答案进行暂存

- submit()

  提交反馈情况到后台

- strMapToObj(strMap)

  将键值对字符串转换为Object对象

- mapToJson(map)

  将map类型的对象转换为json对象

###1.1.2 document_all.html

展示所有的自学材料

- initDoc()


- 初始化所有文档

### 1.1.3 document.html

自学材料筛选界面

- initDoc()

  按时间筛选出最近下载的文档资料

- other(s)

  按章节筛选文档资料

###1.1.4 editor.html

编辑器界面，基于codemirror实现

- initOJInterface()

  初始化对应语言下的代码接口层

- acceptProblem()

  若该题accepted，则加入数据库中

### 1.1.5 f_show.html

算法动画播放界面

- initVideo()

  按照视频编号更新视频列表，同时显示和该视频同属于一个章节的视频

### 1.1.6 flash_all.html

展示所有算法动画的界面

- initVideo()

  显示所有视频资料

### 1.1.7 flash.html

算法动画筛选界面

- initVideo()

  按播放时间筛选出最近播放的10个视频

- other(s)

  按章节对算法动画进行筛选

### 1.1.8 foot.html

设置foot

### 1.1.9 index.html

网站主界面

### 1.1.10 info.html

个人信息界面

- initInfo()

  按照学号显示当前学生的信息

### 1.1.11 login.html

登录界面

在登录界面会首先检查localStorage中sno属性是否为空，若不为空，则跳转到index.html，否则会要求学生输入账号，密码（账号为学生学号，默认密码和学生学号一致）

### 1.1.12  new_chart.html

显示柱状图的界面,基于nvd3.js实现

- createTotalPreviewGraph()

  显示所有学生的预习情况图表

- createPersonPreviewGraph(sno)

  按学号显示当前学生的预习情况图表

- createTotalClassGraph()

  显示所有学生课后学习情况图表

- createPersonClassGraph(sno)

  按学号显示当前学生课后学习情况图表

### 1.1.13 preview_questionare.html

学生课前预习情况调查问卷显示界面

- checkState()

  检查当前学生是否已完成当前问卷

- submit()

  提交问卷回答结果

- initQuestionare()

  以班级为单位，生成知识点问卷，问卷的选项顺序随机生成

- getIndex()

  随机排列数字0-3,以数组的形式返回

###1.1.14 preview.html 

预习界面展示

- initPreviewRequire()

  初始化当前该生的预习要求

- getUrl(type,id)

  得到当前预习任务所对应的url,type为预习任务类型，id为当前预习任务的编号

  type类型解释：

  1：OJ练习题

  2：视频资料

- getTitle()

  ​

