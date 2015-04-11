def word_count(phrase):
    tmp=phrase.split()
    a={}
    for e in tmp:
        if e not in a:
            a[e]=1
        else:
            a[e]+=1
    return a
