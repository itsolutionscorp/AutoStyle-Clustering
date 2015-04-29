def distance(string1,string2):
    if len(string1)!=len(string2):
        print("Not the same length")
        return
    i=0
    count=0
    while(i<len(string1)):
        if string1[i]!=string2[i]:
            count+=1
        i+=1
    return count
