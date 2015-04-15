import re
def word_count(word):
    wlist=re.split(',| |:|!',word)
    wlist=filter(None,wlist)
    
    string={}
    for each in wlist:
        if each.isalpha() or each.isdigit():
            string [each.lower()] = 0
    for each in wlist:
        if each.isalpha() or each.isdigit():
            string [each.lower()] = string [each.lower()]+1
    return string
