def sieve(n):
    rnge = list(range(2, n+1))
    for num1 in rnge:
         for num2 in rnge:
             if num2 % num1 == 0 and num2 != num1 and num2 != 2:
                 rnge.remove(num2)
    return rnge
