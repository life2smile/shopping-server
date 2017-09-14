<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <style type="text/css">

        p {
            text-indent: 2em;
        }

        .love {
            color: #FF4081;
            font-size: xx-large;
            text-align: center;
            margin-bottom: 10px;
        }

        #day {
            font-size: xx-large;
            text-align: center;
            color: #FF4081;

        }

        #title, .title {
            text-align: center;
        }

        .flower {
            text-align: center;
            width: 100%;
        }

        .center {
            position: relative;
            top: 10px;
            left: 50%;
            width: 100%;
            height: 50%;
            -webkit-transform: translateX(-50%);
            -moz-transform: translateX(-50%);
            -ms-transform: translateX(-50%);
            transform: translateX(-50%);
        }



    </style>
</head>

<body>
<div class="center">

    <img class="flower" src="http://img0.imgtn.bdimg.com/it/u=551843976,2346430610&fm=214&gp=0.jpg">
    <p id="title">我们已经在一起 <span id="day"></span> 啦!</p>
    <p class="title">（放心,这个时间准没错，是老公写程序算出来哒~,不信明天再看~）</p>
    <h2>致我最爱的老婆：</h2>
    <p>老婆，今天是七夕节，在有情人都表露彼此心意的时刻，我想着也不能落后。我尝试着想送你一个大大的花束，
        可是花儿真是太贵了！太贵了！最关键是，我想到花儿放不到两天就焉了，买了太可惜了~ ~。而想到作为程序员的我，当然有属于我的style来表达对老婆的爱意。
        所以就想亲自动手送给属于你的这个礼物: 看上面这么大一束，而且永不凋零的玫瑰花！正是代表着我的心意！</p>
    <p>老婆，我们已经携手走过了这么多岁月，而在今年也将迎来了我们爱的结晶--可爱的小宝贝儿哆咪就要出生啦！你我都要升级做
        爸爸妈妈了！在此，首先感谢老婆对这个小家庭无怨无悔的付出！能和你结为夫妻是我这辈子最幸福的选择。人的这一生，不如意着常有，
        谁都想风花雪月，谁都想避劳就逸，可现实终归是五味杂陈的现实，因此当我们回归了
        柴米油盐生活的时候，难免会觉得和理想有很大的落差，进而产生各种各样的抱怨、矛盾和争吵。这个时候希望我们能停下脚步，
        多想想彼此的付出，而不是彼此带来的缺憾，或许一切就豁然开朗了。</p>
    <p>千言万语，最终想表达的正如我在我们婚礼上大声说出来的那句话：以后无论生活怎么样，我们都会不离不弃，直至终老！我爱你，老婆。</p>
    <h2>致我亲爱的哆咪宝贝儿：</h2>
    <p>哎呀，今年乃至这辈子最开心的事儿就是哆咪小宝贝儿的到来啦，爸爸首先希望你能健康、快乐的成长！希望你长大以后能做一个洒脱、阳光、正直、诚信的人。
        最后，倘若能为祖国、为人民做出贡献那自然是再好不过了，但如果
        力不能及，爸爸也希望哆咪宝贝儿能安然的享受属于自己的平凡生活。最重要的是要孝顺妈妈，妈妈十月怀胎甚是辛苦，否则爸爸可不会饶过你哒！
    </p>
    </p>
    <h1 class="love">I LOVE YOU FOREVER !</h1>
</div>
<script>
    var togetherDate = new Date('2014/05/9 00:00:00');
    var now = new Date();
    var diff = now.getTime() - togetherDate.getTime();//时间差的毫秒数
    var day = Math.floor(diff / (24 * 3600 * 1000));
    document.getElementById("day").innerHTML = day + "天";
</script>

</body>
</html>
