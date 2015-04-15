def wordCount(string):
"""
    count = 0
    for c in string:
        if c==" ":
            if previous!=" ":
                count+=1
        previous=c

    if previous!=" ":
        count += 1
    #If the sentence does not end with a space, account for lost last word
"""
    L = [][]
    prev=0
    count=0
    while(i<len(string)):
        if string[i]==" ":
            if string[i-1]!=" ":
                L[count]=string[prev:i]
                count+=1
                prev==i+1
        i+=1
