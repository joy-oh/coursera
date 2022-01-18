import sqlite3

conn = sqlite3.connect('emaildb.sqlite')
cur = conn.cursor()

cur.execute('DROP TABLE IF EXISTS Counts')

cur.execute('CREATE TABLE Counts (org TEXT, count INTEGER)')

fname = input('Enter file name: ')
if(len(fname)< 1): fname = 'mbox.txt'
fh = open(fname)

num = 0
for line in fh:
    if not line.startswith('From: '): continue
    piece = line.split()
    email = piece[1]
    emailSplit = email.split('@')
    org = emailSplit[1]
    cur.execute('SELECT count FROM Counts WHERE org = ?', (org,))
    row = cur.fetchone()
    if row is None:
        cur.execute('INSERT INTO Counts (org, count) VALUES(?, 1)',(org,))
    else:
        cur.execute('UPDATE Counts Set count = count + 1 WHERE org = ?', (org,))
    num += 1
    # if num%10 == 1:
    conn.commit()

sqlstr = 'SELECT org, count FROM Counts ORDER BY count DESC'

for row in cur.execute(sqlstr):
    print((row[0], row[1]))