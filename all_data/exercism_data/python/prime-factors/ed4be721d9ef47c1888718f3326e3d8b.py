def factor(number):
    for n in range(1, number):
        if number % (n + 1) == 0:
            return n + 1

def prime_factors(number):
    pfs = []
    num = number
    while factor(num):
        x = factor(num)
        pfs.append(x)
        num = num / x
    return pfs
    
