import re
import math

def encode(text):
    length = 0
    ret = ''
    temp = re.sub('[^a-z|0-9]', '', text.lower())
    length = int(math.ceil(math.sqrt(len(temp))))
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
