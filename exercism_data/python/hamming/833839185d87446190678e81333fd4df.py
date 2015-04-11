def distance(first,second):
    dist=0
    for i in range(0,len(first)):
        if first[i]!=second[i]:
            dist+=1
    return dist
