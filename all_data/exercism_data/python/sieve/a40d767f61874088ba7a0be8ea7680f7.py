def sieve(limit):
    x=2
    poss=[]
    while x<limit+1:
        poss.append(x)
    reposs=poss
    for num1 in reposs:
        for num2 in poss:
            if num1%num2==0 and num1 != num2:
                poss.remove(num2)
    return poss
