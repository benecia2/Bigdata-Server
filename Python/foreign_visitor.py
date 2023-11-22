import pymysql;
import pandas as pd;

#db연결
dbURL = '127.0.0.1'
dbPort = 3306
dbUser = 'root'
dbPass = '1234'

conn = pymysql.connect(host=dbURL,port=dbPort,user=dbUser, passwd=dbPass,
                       db='btrip', charset='utf8', use_unicode=True)
curs = conn.cursor(pymysql.cursors.DictCursor)
insert_sql = 'insert into foreign_visitor(year,month,visitor,excepted) values(%s,%s,%s,%s)'

df = pd.read_excel('data/foreignvisitors.xlsx', engine='openpyxl',index_col=0)

excepted = 0
for i in df.index:
  year = i
  for col in df.columns:
    month = col
    visitor = df.loc[i][col]
    if i==2023 and col=='Sep':
      excepted=1
    curs.execute(insert_sql,(year,month,visitor,excepted))
conn.commit()
print('insert DataBase')
