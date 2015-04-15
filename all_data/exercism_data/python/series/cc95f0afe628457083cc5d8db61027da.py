

def slices(digits, n):
    if n > len(digits) or n == 0:
        raise ValueError("n must be an integer less than the length of digits and greater than 0")
    ans = []
    sliced_d = digits[:]
    for i in range(len(digits)):
        if len(sliced_d) < n:
            break
        temp = []
        for number in sliced_d[:n]:
            temp.append(int(number))
        ans.append(temp)
        sliced_d = sliced_d[1:]
    return ans
        
        
