
def word_count(str):
    y = []
    x = str.split(' ')
    d = {}
    
    for i in range(0, len(x)):
        if x[i] in y:
            print("")
        else:
            d[x[i]] = x.count(x[i])
            y.append(x[i])
    return d
            

# word_count('joe joe said joe said one')
# word_count('one fish two fish red fish blue fish')
