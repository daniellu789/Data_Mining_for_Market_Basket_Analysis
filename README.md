Data_Mining_for_Market_Basket_Analysis(Association Rules Mining)
======================================
Programming with SQL, JDBC and Java under Windows

Implement the Market Basket analysis by using Apriori algorithm;

Generated frequent item-sets and mined association rules

note: The code is under arm directory and JDBC jar is in project directory.



1.1 Project Overview
----------
For this project, you need to use Java to access an Oracle database and accomplish the
association rule data mining task using the Apiriori algorithm. Please do your own
implementation. We will be checking similarities between the solutions. For this
project, you will be asked to write code that mines frequent itemsets and association rules.
The data set consists of two database tables:
1. Trans(TransID, ItemID)
2. Items(ItemID, ItemName)
The rst table lists all of the database transactions that you will be mining, and the second
table gives the actual names for the items that you are mining (such as "Merlot Cheddar",
"Red Potatoes", etc.) Here are some of the vocabulary you'll need to understand this project:
 Itemset is a set of database items.
 Transaction is a set of database items that were purchased together, perhaps as part
of a cash register transaction.
 Frequent Itemset (FI) is a set of database items that all appear in at least s% of
the database transactions, where s is the user-dened support level.
 Candidate Frequent Itemset is an itemset that might be frequent, but we have not
yet counted how many transactions it appears in.
 Apriori Rule is a rule that is used to construct candidate FIs of size i + 1 from all
FIs of size i.
 Association Rule (AR) is a rule of the form fMerlot Cheddar, Red Potatoesg !
fRomanog. All of the items in the rule must appear at least s% of the time (that is,
all of the items in the association rule must together constitute an FI). Also, if the
left-hand-size of the rule is present in a transaction, then the right-hand-side must also
be present c% of the time, where c is is the user-specied condence.
1.2 Tasks
-------------
There are 4 tasks you need to nish in this project:
 In Task 1, given a specic support level(such as s = 45%), your program should re-
turn all of the (single)items in the database that appear at least s% of the time. So
your output might be:The following items appear in 45% of the database transactions:
fMerlot Cheddarg, fRomanog, fRed Potatoesg.
2
 In Task 2, given a specic support level(such as s= 45%), your program should return
all of the single items or pairs of items that appear in the database at least s% of the
time. So your output might be: The following items appear in 45% of the database
transactions: fMerlot Cheddarg, fRomanog, fRed Potatoesg, fMerlot Cheddar, Ro-
manog, fMerlot Cheddar, Red Potatoesg
 In Task 3, given a specic support level and the maximum FI size, your program should
nd all of the frequent itemsets that are no larger (in terms of the number of items
that they contain) than the user-specied size.
 In Task 4, this task is about mining association rules. Given a specic support level,
a specic condence and the maximum number of items in an association rule, your
program would then rst nd all frequent itemsets at the user-specied size, and then
use those FIs to generate all possible association rules.
1.3 A Quick Note and Hints
You are not allowed to maintain and manipulate data structures in Java, if you can avoid
it. Use the database as much as you can!
For example, one of the things that you'll need to do to is to apply the "apriori rule" to
obtain candidate FIs of size (i + 1) from all of the actual FIs of size i. This rule states:
"An itemset of size (i + 1) cannot appear in s% of the database transactions unless all
of its subsets of size i appeared in s% of the database transactions."
Of course, you can do this by writing a bunch of code in Java. But you can also do it by
sending SQL queries o to the database, which could be a heck of a lot easier. For example,
imagine that you have created a database table FISet(ISetID, ItemID) that you use to hold
all of the FIs of size i (that is, each FI of size i has a unique ISetID value, and the i dierent
items contained in each itemset are spread across i dierent tuples in FISet; those i tuples
all have the same ISetID). The rst thing that you'll want to do is to nd pairs of size-i FIs
that can be "glued together" to nd a candidate FI of size i + 1 (for example: the itemsets
ABCD and BCDE can be glued together to form ABCDE). You can nd all such pairs with
the following SQL query:
SELECT DISTINCT IS1 . ISet ID AS ID1 , IS2 . ISet ID AS ID2
FROM FISet IS1 , FISet IS2
WHERE IS1 . ISet ID > IS2 . ISet ID AND i + 1 =
(SELECT COUNT (DISTINCT ItemID)
FROM FISet IS3
WHERE IS1 . ISet ID = IS3 . ISet ID OR IS3 . ISet ID = IS2 . ISet ID ) ;
Given a specifc ID1, ID2 pair, you can get the list of ItemIDs that results from combining
them with the following query:
SELECT DISTINCT ItemID
FROM FISet IS
WHERE IS . ISet ID = ID1 OR IS . ISet ID = ID2 ;
3
Once you have such a candidate itemset, but before you actually do to the database
to see how many times it appears, you'll want to verify that all of its subsets of size i are
also FIs { if they are not, then according to the apriori rule, the candidate itemset cannot
possibly be an FI. You can do this check using the database as well. If all of the candidate
itemsets's item IDs are stored in the database table TEMP, then the following query will
tell you how many of its size-i subsets are FIs:
SELECT COUNT( )
FROM (SELECT FISet . ISet ID
FROM TEMP, FISet
WHERE TEMP. ItemID = FISet . ItemID
GROUP BY FISet . ISet ID
HAVING COUNT(  ) = i ) ;
If this query does not come back with the number i + 1, then the candidate itemset that is
in TEMP cannot possibly be frequent (because it has broken the apriori rule) and you don't
have to see how many times it appears in the database.
These are just a few examples of how, by using the database to manage your data, you
may be able to reduce the amount of coding that you need to do in the external language.
2 Input and Output
-----------
You must exactly follow the input and output format described in the following subsections.
If you failed to follow the format, your program may not be able to parse the input le
and the TAs' grading script may not be able to interpret your output. Also the input les,
output les and your code les should be in the same directory. Don't add any extra le
path except the le name to the path argument when program tries to read/write some les.
2.1 Input
--------
There are three input les, namely items.dat, trans.dat and system.in.
The system.in contains one and only one test case for each task.
The rst line in the system.in contains the your credential to access the oracle database.
The second line, third line, fourth line, fth line are the parameters for TASK1, TASK2,
TASK3, TASK4 respectively.
username = your user name, password = your password
TASK1: support = 6%
TASK2: support = 7%
TASK3: support = 7%, size = 3
TASK4: support = 2%, condence = 95%, size = 3
2.2 Output
------------
There is one output le for each task. Your program should generate four output les,
namely, system.out.1, system.out.2, system.out.3 and system.out.4.
4
Sample output for TASK1:
fMerlot Cheddarg, s=45%
fRed Potatoesg, s=57%
Sample output for TASK2:
fMerlot Cheddarg, s=10%
fMerlot Cheddar, Romanog, s=15%
fMerlot Cheddar, Red Potatoesg, s=20%
Sample output for TASK3:
fMerlot Cheddar, Romanog, s=15%
fMerlot Cheddar, Red Potatoesg, s=20%
fMerlot Cheddar, Red Potatoes, Romanog, s=21%
Sample output for TASK4:
ffMerlot Cheddar, Red Potatoesg 􀀀 > fRomanogg, s=10%, c=11%
ffMerlot Cheddar, Diaperg 􀀀 > fBeergg, s=11%, c=33%
2.3 Project Report
------------
You are required to submit a 1-2 pages TXT report. Name it report.txt. Note the small
case 'r' and NOT capital 'R'. Your report must contain a brief description telling us how
you developed your code, what diculties you faced and what you learned. Remember this
has to be a simple text le and not a MSWORD or PDF le. It is recommended that you
use vi or gedit or pico/nano applications in UNIX/Linux to develop your report.
2.4 Additional Instructions
------------------
1. You should tar all you java code les including the txt report in a single tar le using
this command on Linux/SunOS:
tar cvf arm.tar <le list to compress and add to archive>
2. Your project must be named 'arm.tar' and also your java le which contains the main
function must be named 'arm.java'.
3 Automatic Testing Script
The TAs will run the following script to grade your program on the CISE Linux machines.
We will use the same data les as the provided data les. But the output les will not be
provided.
#!/bin/bash
source /usr/local/etc/ora11.csh
tar xvf arm.tar;
./plagiarism-detector
rm *.class system.out.* report.txt
cp /path/to/item.dat .
cp /path/to/trans.dat .
javac -cp ojdbc5.jar arm.java
5
cp /path/to/system.in .
cp /path/to/item.dat .
cp /path/to/trans.dat .
cp /path/to/correct.system.out-1 .
cp /path/to/correct.system.out-2 .
cp /path/to/correct.system.out-3 .
java -cp ojdbc5.jar:. arm
java autograder
cat report.txt
4 Grading Guidelines:
It is your responsibility to make sure you follow the the input and output format. Your
project will be graded 0 if either your program cannot read the input les provided by the
TAs or the TAs' autograder cannot parse your output les.
 TASK1 15%
 TASK2 15%
 TASK3 15%
 TASK4 30%
 Project report 25%
5 Late Submission Policy:
Late submissions are not encouraged but we rather have you submit a working version than
submit a version that is broken or worse not submit at all. Every late submission will be
penalized 10% for each day late for up to a maximum of 3 days from the due date.
6 Helpful Resources:
6.1 Access CISE Oracle database
You need to create a CISE Oracle account to access the CISE Oracle database. Please refer
to the following CISE website:
http://www.cise.u
.edu/help/database/oracle.shtml
However some information in the above website is not up to date. The location for the jdbc
driver is no longer valid.
Note: To use Oracle's JDBC on Department Solaris machines, add the following to your
CLASSPATH: /usr/local/libexec/oracle/app/oracle/product/11.2.0/client 1/ojdbc5.jar.
The solution for you is just downloading the attached 'ojdbc5.jar' and put it into your source
code.
6
6.2 How to execute sql script in Java
You will need to write procedures to implement the core part of this project. The following
two lines of code enable you to import sql script containing procedure code to the database.
Process p = Runtime.getRuntime().exec("sqlplus username@orcl/passwd @arm.sql");
p.waitFor();
Please append "exit;" to the end of your procedure code le 'arm.sql'. This line of code
enable your to exit the sqlplus environment.
6.3 Tutorials
You can have a look at the following tutorials that might be helpful in doing this project:
1. http://en.m.wikipedia.org/wiki/Apriori algorithm#section language
2. http://infolab.stanford.edu/ ullman/fcdb/oracle/or-jdbc.html
3. http://docs.oracle.com/cd/B25329 01/doc/appdev.102/b25108/xedev jdbc.htm
4. JDBC is covered a bit in your textbook, there are tons of books on JDBC at UF
libraries, and Google will get you more info on JDBC than you ever wanted to know.
7
