一、  Ready  Work:
    1.设置Vm与主机间的共享网络
    http://zhidao.baidu.com/question/41119027.html?qbl=relate_question_0&word=vm%D0%E9%C4%E2%BB%FA%CD%F8%C2%E7%C9%E8%D6%C3
    2.切换操作界面（仅限Desktop版本系统）
        共有7个， tty1 ~ tty7 ，切换的方式为 Crtl + Alt + [F1]~[F7]，其中， [F7] 为图形介面的使用。
    3.Ubuntu 初始化root密码的方法
        设置初始账户登录之后，输入：sudo passwd root
        输入初始用户的密码
        输入要设置的root的密码
        再次输入root密码  --->完成设置
        此时输入 su root，输入密码，即可切换到root用户
        也可以输入exit，再使用root账户登录系统

二、  重要的几个热键[Tab], [ctrl]-c, [ctrl]-d
1.  xx[Tab][Tab]    (连续两次)       命令提示，或文件列出
    [Tab] 接在一串指令的第一个字的后面，则为命令补全；
    [Tab] 接在一串指令的第二个字以后时，则为‘档案补齐’！
2. Ctrl+C 结束命令
3. Ctrl+D 相当于exit/quit，退出编辑（文本）



三、  基本命令：
1. uname -r 查看系统Linux版本
   date 时间：
    date +%Y/%m/%d      格式化时间显示 "年/月/日"

2. cal 日历：
    cal [month] [year]  产看指定年月的日历

3. man 查看命令介绍
    例如：man date
    会看到date后面有个代号“1”，表示这个命令是可执行命令，具体数字对应的关系如下，158为关键点
        代号 代表内容
       ~1   使用者在shell环境中可以操作的指令或可执行档
        2   系统核心可呼叫的函数与工具等
        3   一些常用的函数(function)与函式库(library)，大部分为C的函式库(libc)
        4   装置档案的说明，通常在/dev下的档案
       ~5   设定档或者是某些档案的格式
        6   游戏(games)
        7   惯例与协定等，例如Linux档案系统、网路协定、ASCII code等等的说明
       ~8   系统管理员可用的管理指令
        9   跟kernel有关的文件


4. ls -al   结果详解

        [root@www ~]# ls -al
        total 156
        drwxr-x---   4    root   root     4096   Sep  8 14:06 .
        drwxr-xr-x  23    root   root     4096   Sep  8 14:21 ..
        -rw-------   1    root   root     1474   Sep  4 18:27 anaconda-ks.cfg
        -rw-------   1    root   root      199   Sep  8 17:14 .bash_history
        -rw-r--r--   1    root   root       24   Jan  6  2007 .bash_logout
        -rw-r--r--   1    root   root      191   Jan  6  2007 .bash_profile
        -rw-r--r--   1    root   root      176   Jan  6  2007 .bashrc
        -rw-r--r--   1    root   root      100   Jan  6  2007 .cshrc
        drwx------   3    root   root     4096   Sep  5 10:37 .gconf      <=范例说明处
        drwx------   2    root   root     4096   Sep  5 14:09 .gconfd
        -rw-r--r--   1    root   root    42304   Sep  4 18:26 install.log <=范例说明处
        -rw-r--r--   1    root   root     5661   Sep  4 18:25 install.log.syslog

        [    1   ][  2 ][   3  ][  4 ][    5   ][     6     ][       7          ]
        [  权限  ][连结][拥有者][群组][档案容量][  修改日期 ][      档名        ]


    4.1    第一个字元代表这个档案是‘目录、档案或连结档等等’：

         当为[ d ]则是目录，例如上表档名为‘.gconf’的那一行；
         当为[ - ]则是档案，例如上表档名为‘install.log’那一行；
         若是[ l ]则表示为连结档(link file)；
         若是[ b ]则表示为装置档里面的可供储存的周边设备(可随机存取装置)；
         若是[ c ]则表示为装置档里面的序列埠设备，例如键盘、滑鼠(一次性读取装置)。

         4.1.1  档案(此处档案就是文件的意思）权限关键字：    r  可读    w  可以编辑、新增或者是修改该档案的内容(但不含删除该档案)；    x  可执行
            文件权限修改：
            chgrp ：改变档案所属群组
                chgrp [-R] dirname/filename ...
                    选项与参数：
                    -R : 进行递归(recursive)的持续变更，亦即连同次目录下的所有档案、目录
                         都更新成为这个群组之意。常常用在变更某一目录内所有的档案之情况。
                例：chgrp users install.log
                    修改install.log的档案所属群组为users

            chown ：改变档案拥有者
                语法：   chown [-R] 帐号名称 档案或目录
                        chown [-R] 帐号名称:群组名称 档案或目录

            chmod ：改变档案的权限, SUID, SGID, SBIT等等的特性
                <1> 数字类型改变档案权限
                    数字字符对应关系：
                             r:4
                             w:2
                             x:1
                    语法： chmod [-R] xyz 档案或目录
                    例子：
                    [root@www ~]# ls -al .bashrc
                    -rw-r--r--  1 root root 395 Jul  4 11:45 .bashrc
                    [root@www ~]# chmod 777 .bashrc
                    [root@www ~]# ls -al .bashrc
                    -rwxrwxrwx  1 root root 395 Jul  4 11:45 .bashrc

                    那如果要将权限变成‘ -rwxr-xr-- ’呢？那么权限的分数就成为 [4+2+1][4+0+1][4+0+0]=754 啰！所以你需要下达‘ chmod 754 filename’
                <2> 符号类型改变档案权限
                    栗子：
                    <2.1> user (u)：具有可读、可写、可执行的权限；
                          group 与 others (g/o)：具有可读与执行的权限。
                          执行语句如下： chmod  u=rwx,go=rx  文件名
                    <2.2> 假如是‘ -rwxr-xr-- ’这样的权限呢？可以使用‘ chmod u=rwx,g=rx,o=r filename ’来设定。
                    <2.3> 此外，如果我不知道原先的档案属性，而我只想要增加.bashrc这个档案的每个人均可写入的权限， 那么我就可以使用：
                            chmod a+w .bashrc   (a+w代表给所有人（a）添加写权限）
                    <2.4> 如果是要将权限去掉而不更动其他已存在的权限呢？例如要拿掉全部人的可执行权限，则：
                          chmod  a-x  .bashrc

         4.1.2  目录权限关键字：
                    r   可读---即，使用ls命令可以查到该目录（但并不意味着能进入该目录，进入目录的权限是x）
                    w   可写---即，添加/删除/重命名/移动 目录下的文件或文件夹的权力
                    x   可执行---即，可进入
              注：：：：：如果用户Bob对文件A的权限为0，但是Bob拥有该文件A所在目录的w权限，该用户依然可以对A进行重命名、删除、移动的权限！！！

    例子：
        [-][rwx][r-x][r--]
         1  234  567  890
        1 为：代表这个档名为目录或档案，本例中为档案(-)；
       234为：拥有者的权限，本例中为可读、可写、可执行(rwx)；
       567为：同群组使用者权限，本例中为可读可执行(rx)；
       890为：其他使用者权限，本例中为可读(r)


5. 目录相关操作：
	5.1 目录基础部分
		.         代表此层目录
		..        代表上一层目录
		-         代表前一个工作目录
		~         代表‘目前使用者身份’所在的家目录
		~account  代表 account 这个使用者的家目录(account是个帐号名称)

	5.2 命令部分
		5.2.1 cd：变换目录

		5.2.2 pwd：显示目前的目录
			pwd -P  显示连结档
			其实有趣的是那个 -P 的选项啦！他可以让我们取得正确的目录名称，而不是以连结档的路径来显示的。
			如果你使用的是CentOS 5.x的话，刚刚好/var/mail是/var/spool/mail的连结档， 所以，透过到/var/mail下达pwd -P就能够知道这个选项的意义啰～ ^_^

		5.2.3 mkdir：建立一个新的目录
			[root@www ~]# mkdir [-mp] 目录名称

			选项与参数：
			-m ：设定档案的权限喔！直接设定，不需要看预设权限 (umask) 的脸色～
			-p ：帮助你直接将所需要的目录(包含上层目录)递归建立起来！

			范例：请到/tmp底下尝试建立数个新目录看看：
			[root@www ~]# cd /tmp
			[root@www tmp]# mkdir test    <==建立一名为 test 的新目录
			[root@www tmp]# mkdir test1/test2/test3/test4
			mkdir: cannot create directory `test1/test2/test3/test4':
			No such file or directory       <== 没办法直接建立此目录啊！

			[root@www tmp]# mkdir -p test1/test2/test3/test4
			# 加了这个 -p 的选项，可以自行帮你建立多层目录！

			范例：建立权限为rwx--x--x的目录
			[root@www tmp]# mkdir -m 711 test2
			[root@www tmp]# ls -l
			drwxr-xr-x  3 root  root 4096 Jul 18 12:50 test
			drwxr-xr-x  3 root  root 4096 Jul 18 12:53 test1
			drwx--x--x  2 root  root 4096 Jul 18 12:54 test2

			# 仔细看上面的权限部分，如果没有加上 -m 来强制设定属性，系统会使用预设属性。
			# 那么你的预设属性为何？这要透过底下介绍的 umask 才能了解喔！ ^_^

		5.2.4 rmdir：删除一个“空”的目录
			那如果要将所有目录下的东西都杀掉呢？！ 这个时候就必须使用‘ rm -r test ’啰！

			[root@www ~]# rmdir [-p] 目录名称

			选项与参数：
			-p ：连同上层‘空的’目录也一起删除

			范例：将于mkdir范例中建立的目录(/tmp底下)删除掉！
			[root@www tmp]# ls -l   <==看看有多少目录存在？
			drwxr-xr-x  3 root  root 4096 Jul 18 12:50 test
			drwxr-xr-x  3 root  root 4096 Jul 18 12:53 test1
			drwx--x--x  2 root  root 4096 Jul 18 12:54 test2
			[root@www tmp]# rmdir test   <==可直接删除掉，没问题
			[root@www tmp]# rmdir test1  <==因为尚有内容，所以无法删除！

			rmdir: `test1': Directory not empty
			[root@www tmp]# rmdir -p test1/test2/test3/test4
			[root@www tmp]# ls -l        <==您看看，底下的输出中test与test1不见了！
			drwx--x--x  2 root  root 4096 Jul 18 12:54 test2
			# 瞧！利用 -p 这个选项，立刻就可以将 test1/test2/test3/test4 一次删除～
			# 不过要注意的是，这个 rmdir 仅能‘删除空的目录’喔！


6. 档案与目录管理：
	6.1 档案与目录的检视： ls
		[root@www ~]# ls [-aAdfFhilnrRSt] 目录名称
		[root@www ~]# ls [--color={never,auto,always}] 目录名称
		[root@www ~]# ls [--full-time] 目录名称

		选项与参数：
		-a  ：全部的档案，连同隐藏档( 开头为 . 的档案) 一起列出来(常用)
		-A  ：全部的档案，连同隐藏档，但不包括 . 与 .. 这两个目录
		-d  ：仅列出目录本身，而不是列出目录内的档案资料(常用)
		-f  ：直接列出结果，而不进行排序 (ls 预设会以档名排序！)
		-F  ：根据档案、目录等资讯，给予附加资料结构，例如：
			  *:代表可执行档； /:代表目录； =:代表 socket 档案； |:代表 FIFO 档案；
		-h  ：将档案容量以人类较易读的方式(例如 GB, KB 等等)列出来；
		-i  ：列出 inode 号码，inode 的意义下一章将会介绍；
		-l  ：长资料串列出，包含档案的属性与权限等等资料；(常用)
		-n  ：列出 UID 与 GID 而非使用者与群组的名称 (UID与GID会在帐号管理提到！)
		-r  ：将排序结果反向输出，例如：原本档名由小到大，反向则为由大到小；
		-R  ：连同子目录内容一起列出来，等于该目录下的所有档案都会显示出来；
		-S  ：以档案容量大小排序，而不是用档名排序；
		-t  ：依时间排序，而不是用档名。
		--color=never  ：不要依据档案特性给予颜色显示；
		--color=always ：显示颜色
		--color=auto   ：让系统自行依据设定来判断是否给予颜色
		--full-time    ：以完整时间模式 (包含年、月、日、时、分) 输出
		--time={atime,ctime} ：输出 access 时间或改变权限属性时间 (ctime)
							   而非内容变更时间 (modification time)

	6.2 复制、删除与移动： cp, rm, mv


7. vi(文本编辑)/vim(程式编辑器)
	语法： vi  filename
	
	7.1 vi 常用快捷键
		基础：	刚进入编辑器，是命令模式，此时输入 i 进入编辑模式，Esc键退出编辑模式返回命令模式。
				命令模式下，输入 ：进入底部命令行模式，此时输入 :wq 即可保存文件并退出
		
		//命令模式下常用快捷键：
			G		移动到这个档案的最后一行(常用)
			nG		n 为数字。移动到这个档案的第 n 行。例如 20G 则会移动到这个档案的第 20 行(可配合 :set nu)
			gg		移动到这个档案的第一行，相当于 1G 啊！ (常用)
			n<space>	那个 n 表示『数字』，例如 20 。按下数字后再按空格键，光标会向右移动这一行的 n 个字符。例如 20<space> 则光标会向后面移动 20 个字符距离。
			n<Enter>	n 为数字。光标向下移动 n 行(常用)
			
			//搜索:
				/word	向光标之下寻找一个名称为 word 的字符串。例如要在档案内搜寻 vbird 这个字符串，就输入 /vbird 即可！ (常用)
				?word	向光标之上寻找一个字符串名称为 word 的字符串。
				n		这个 n 是英文按键。代表重复前一个搜寻的动作。举例来说， 如果刚刚我们执行 /vbird 去向下搜寻 vbird 这个字符串，则按下 n 后，会向下继续搜寻下一个名称为 vbird 的字符串。如果是执行 ?vbird 的话，那么按下 n 		则会向上继续搜寻名称为 vbird 的字符串！
				N		这个 N 是英文按键。与 n 刚好相反，为『反向』进行前一个搜寻动作。 例如 /vbird 后，按下 N 则表示『向上』搜寻 vbird 。
				
			//替换：
				:n1,n2s/word1/word2/g	替换n1行至n2行的word1字符串为word2。例来说，在 100 到 200 行之间搜寻 vbird 并取代为 VBIRD 则：『:100,200s/vbird/VBIRD/g』。(常用)
				:1,$s/word1/word2/g		替换第一行至最后一行的所有wrod1字符串为word2
				:1,$s/word1/word2/gc	替换第一行至最后一行的所有wrod1字符串为word2，且在取代前显示提示字符给用户确认 (confirm) 是否需要取代！(常用)	
				
			//删除、复制与贴上
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

