def slices(str,x):
    numlist = map(int,list(str))     #convert to list of digits
    nslices = len(numlist)-x+1
    if nslices <= 0 or nslices > len(numlist):
        raise ValueError
    slicelist = []
    for i in range(nslices):
        slicelist.append(numlist[i:i+x])
    return slicelist
