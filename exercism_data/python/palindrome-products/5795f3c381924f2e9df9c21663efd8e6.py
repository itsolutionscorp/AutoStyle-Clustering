def smallest_palindrome(max_factor, min_factor=0):
    return palindrome_computer(max_factor, min_factor, 'MIN')

def largest_palindrome(max_factor, min_factor=0):
    return palindrome_computer(max_factor, min_factor, 'MAX')

def palindrome_computer(fmax, fmin, op):
    possibles = [(i*j,i,j) for i in xrange(fmin, fmax+1) for j in xrange(i, fmax+1)  if ispalindrom(i*j)]
    if op == 'MAX':
        value = max([possibles[i][0] for i in range(len(possibles))])
    else:
        value = min([possibles[i][0] for i in range(len(possibles))])

    for p in possibles:
        if p[0] == value:
            return p[0], {p[1], p[2]}

def ispalindrom(val):
    return str(val) == str(val)[::-1]
