import re

def parse_binary(num):
    if re.compile('[^01]').search(num):
        raise ValueError('non binary input')
    
    return sum(int(num[i]) * 2 ** (len(num) - i - 1) for i in range(len(num)))
