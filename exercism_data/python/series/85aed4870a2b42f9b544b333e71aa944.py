def slices(in_string, number):
    if number > len(in_string):
        raise ValueError("Length of series must not be larger than string")
    if number == 0:
        raise ValueError("Length of series must not be zero")
    outlist=[]
    for i in range(0,len(in_string)-number+1):
        temp = list(in_string[i:number+i])
        outlist.append([int(char) for char in temp])

   
    return outlist
        
print slices("0123",2)
