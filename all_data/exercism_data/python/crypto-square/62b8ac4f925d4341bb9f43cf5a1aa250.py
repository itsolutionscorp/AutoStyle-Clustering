import math

def encode(d):
    
    d = [letter.lower() for letter in d if letter.isalnum()]
    
    temp = [[]]
    rowN = int(math.ceil(math.sqrt(len(d))))
    
    for i in range(len(d)):
        if len(temp[-1]) == rowN:
            temp.append([])
        temp[-1].append(d[i])
        
    ans = ''
    
    for j in range(rowN):
        for i in range(len(temp)):
            if j < len(temp[i]):
                ans += temp[i][j]
        ans += ' '
        
    return ans.strip()
