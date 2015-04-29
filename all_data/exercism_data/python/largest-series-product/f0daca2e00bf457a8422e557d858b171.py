def largest_product(arr, large):
    if large>len(arr):
        raise ValueError("Error")

    return max([prod(i) for i in slices(arr,large)])

def prod(list): 
    prd = 1
    for i in list:
        prd *= i
    return prd

def substr(sub,i,c):
    return sub[i:i+c]

def slices(toSlice,large):
    ln = len(toSlice)
    if large>ln:
        raise ValueError("Error")

    slices = []
    for i in range(ln-large+1):
        tmp = []
        for j in range(large):
            tmp.append(int(toSlice[i+j]))
        slices.append(tmp)

    return slices
