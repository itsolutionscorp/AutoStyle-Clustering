def roman(arabic):
    precedence_list = ['I', 'V', 'X', 'L', 'C', 'D', 'M']
    value_list = [1, 5, 10, 50, 100, 500, 1000]
    n_processed = 0
    n = 0
    a, remainder = '', arabic
    
    while n_processed < len(arabic):
        a, remainder = remainder[0],remainder[1:]
        
        if remainder == '':
            n += value_list[precedence_list.index(a)]
            break
        
        if precedence_list.index(a) < precedence_list.index(remainder[0]):
            n -= value_list[precedence_list.index(a)]
            n += value_list[precedence_list.index(remainder[0])]
            remainder = remainder[1:]
            n_processed += 2
        else:
            n += value_list[precedence_list.index(a)]
            n_processed += 1
            
    return n

    
def numeral(n):
    precedence_list = ['I', 'IV', 'V', 'IX', 'X', 'XL', 'L', 'XC', 'C', 'CD', 'D', 'CM', 'M']
    value_list = [1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000]
    r = ''
    m = n
    
    for i in range(len(precedence_list)-1,-1,-1):
        for x in range(n/value_list[i]):
            r += precedence_list[i]
        n -= value_list[i]*(n/value_list[i])

    return r


def tmp():
    for i in range(n/1000):
        r += 'M'
    n = n - 1000*(n/1000)
    for i in range(n/900):
        r += 'CM'
    n = n - 900*(n/900)
    for i in range(n/500):
        r += 'D'
    n = n - 500*(n/500)
    for i in range(n/400):
        r += 'CD'
    n = n - 400*(n/400)
    for i in range(n/100):
        r += 'C'
    n = n - 100*(n/100)
    for i in range(n/90):
        r += 'XC'
    n = n - 90*(n/90)
    for i in range(n/50):
        r += 'L'
    n = n - 50*(n/50)
    for i in range(n/40):
        r += 'XL'
    n = n - 40*(n/40)
    for i in range(n/10):
        r += 'X'
    n = n - 10*(n/10)
    for i in range(n/9):
        r += 'IX'
    n = n - 9*(n/9)
    for i in range(n/5):
        r += 'V'
    n = n - 5*(n/5)
    for i in range(n/4):
        r += 'IV'
    n = n - 4*(n/4)
    for i in range(n/1):
        r += 'I'
    n = n - 1*(n/1)
