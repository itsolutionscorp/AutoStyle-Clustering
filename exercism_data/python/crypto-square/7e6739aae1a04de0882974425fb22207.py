import math

def encode(d):
    
    d = [letter.lower() for letter in d if letter.isalnum()]
    
    rowN = int(math.ceil(math.sqrt(len(d))))
    
    ans = ''
    
    for j in range(rowN):
        for i in range(len(d)/rowN+1):
            if (i*rowN+j) < len(d): ans += d[i*rowN+j]
        ans += ' '
        
    return ans.strip()
