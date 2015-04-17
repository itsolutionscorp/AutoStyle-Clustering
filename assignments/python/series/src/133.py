def slices(string,n):
    if (n>len(string))or n <=0 or type(n)!=int:
        raise ValueError
    off_set=0
    ans=[]
    for a in range(len(string)-n+1):
        temp_piece=[]
        for m in range(n):
            temp_piece.append(int(string[m+off_set]))
        ans.append(temp_piece)
        off_set += 1
    return ans
