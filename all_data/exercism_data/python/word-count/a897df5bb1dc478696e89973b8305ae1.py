import re
def word_count(word):
    wlist=re.split(',| |:|!',word)
    wlist=filter(None,wlist)
    
    wcount={}
    for each in wlist:
        if each.isalpha() or each.isdigit():
            if each.lower() in wcount.keys():
                wcount [each.lower()] += 1
            else:
                 wcount [each.lower()] = 1
    return wcount
