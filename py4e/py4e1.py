hrs=input("enter hours:")
h=float(hrs)
rate=10.50
if h>40:
    pay=40*rate+(h-40)*rate*1.5
    print(pay)
else:
    pay=h*rate
    print(pay)