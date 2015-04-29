import re
def word_count(p):
    d={}
    for x in p.split():
        x = re.sub('\W','',x).lower()
        if x in d:
            d[x]=d[x]+1
        elif x != '':
            d[x]=1
    return d
