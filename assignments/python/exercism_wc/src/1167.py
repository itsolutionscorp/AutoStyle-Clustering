def wordCount(string):
    L = []
    prev=0
    count=0
    i = 0
    while(i<len(string)):
        if string[i]==" ":
            if string[i-1]!=" ":
                L[count]=string[prev:i]
                count+=1
                prev==i+1
        i+=1
