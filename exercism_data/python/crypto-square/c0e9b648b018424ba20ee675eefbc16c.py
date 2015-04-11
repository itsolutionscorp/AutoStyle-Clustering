from math import sqrt, ceil
import string

def encode(s):
    s = ''.join(filter(lambda x: x in string.ascii_letters + string.digits, s.lower()))
    sqSize = ceil(sqrt(len(s)))
    result = []
    for i in range(0, sqSize):
        result.append(s[i:len(s):sqSize])
    return ' '.join(result)
