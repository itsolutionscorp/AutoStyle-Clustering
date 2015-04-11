import re

def word_count(a_string):

    a_string = a_string.lower()

    words = re.findall('\b*([^\W+]+)\b*', a_string)
        
    w = {}
    for x in words:
        if x in w.keys():
            w[x] += 1
        else:
            w[x] = 1

    return w
    
