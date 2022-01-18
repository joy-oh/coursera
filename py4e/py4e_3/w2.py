import re
handle = open('regex_sum_1246910.txt')
nums=[]
for line in handle:
    nums=nums+re.findall("[0-9]+",line)
total = 0
for num in nums:
    total += int(num)
print(total)
# file=open("regex_sum_1246910.txt")
# print(sum([int(num) for num in re.findall('[0-9]+',file.read())]))