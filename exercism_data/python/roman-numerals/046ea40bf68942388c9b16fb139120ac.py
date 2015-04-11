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
