# pokergame
规则包含：
散牌<一对<两对<三条<顺子<同花<葫芦<同花顺，与老师的功能稍有差别。
并且，两人都从同一副扑克牌中抽取，若两人有牌一样，则显示“牌数不合法”；
测试：
输入: Black: 2H3D5S9CKD White: 2C3H4S8CAH 输出: White wins
输入: Black: 2H4S4C2D4H White: 2S8SASQS3S 输出: Black wins（B牌型为葫芦）
输入: Black: 2H3H5H9HKH White: 2C3H4S5C6H 输出: 牌数不合法（B和W均有3H）
输入: Black: 2H3D5S9CKD White: 2D3H5C9SKH 输出: Tie
