def word_count(x):
    x=x.replace("\n"," ")
    while "  " in x:
        x=x.replace("  "," ")
    x=x.split(" ")
    a=0
    words={}
    while a<len(x):
        word=x[a]
        if x.count(word)>1:
            words[word]=x.count(word)
            while x.count(word)!=0:
                x.remove(word)
        else:
            words[word]=1
            x.remove(word)
    return words
