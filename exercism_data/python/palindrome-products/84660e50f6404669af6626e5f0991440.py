def smallest_palindrome(max_factor, min_factor = 0):
    
    items = []
    
    for i in range(min_factor,max_factor+1):
        for j in range(min_factor,max_factor+1):
            if palindrome(i*j): items.append((i*j,[i,j]))
            
    stuff = sorted(items,key = lambda x: x[0])
    
    ans = []
    
    for item in stuff:
        
        if item[0] == stuff[0][0]: ans += item[1]           
        else: break
        
    return (stuff[0][0],ans)

def largest_palindrome(max_factor, min_factor = 0):
    
    items = []
    
    for i in range(max_factor,min_factor-1,-1):
        for j in range(max_factor,min_factor-1,-1):
            if palindrome(i*j): items.append((i*j,[i,j]))
            
    stuff = sorted(items,key = lambda x: x[0], reverse=True)

    ans = []

    for item in stuff:

        if item[0] == stuff[0][0]: ans += item[1]
        else: break
        
    return (stuff[0][0],ans)

def palindrome(s):
    """ A quick function to determine if a string
        is a palindrome or not.
    """
    if type(s) != str: s = str(s)
    
    for i in range(len(s)/2):
        if s[i] != s[-(i+1)]: return False
        
    return True

def factorize(n):
    return (0,0)
