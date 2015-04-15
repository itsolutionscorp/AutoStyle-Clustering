import re

def encode(text):
    length = 0
    ret = ''
    temp = re.sub('[^a-z|0-9]', '', text.lower())
    for i in range(len(temp)):
        if len(temp) <= i * i:
            length = i
            break
    i = 0
    tempList = [''] * length
    for x in temp:
        tempList[i] += x
        i += 1
        if i == length:
            i = 0

    for v in tempList:
        ret += v
        ret += ' '
    return ret.rstrip()
