import operator
def smallest_palindrome(max_factor,min_factor=0):
    return palindrome(max_factor,min_factor,operator.lt,max_factor**2)

def largest_palindrome(max_factor,min_factor=0):
    return palindrome(max_factor,min_factor,operator.gt,min_factor**2)

def palindrome(max_factor,min_factor,op,lim):
    for i in range(min_factor,max_factor):
        for j in range(i,max_factor+1):
            if i==0:
                break
            prod = str(i*j)
            #first and second half(reversed)
            first = prod[:len(prod)//2+(len(prod)%2)]
            second = (prod[len(prod)//2:])[::-1]
            if first == second:
                    #op is gt or lt depending on the caller
                    if op(int(prod),lim):
                        sav_prod = lim = int(prod)
                        saved = (i,j)
    return sav_prod,saved
