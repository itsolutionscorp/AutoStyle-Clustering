def sieve(limit):  
    list = []  
    for i in range(2, limit + 1):
        isPrime = True
        for j in list:
            if i % j == 0:
                isPrime = False
                break
        if isPrime == True:
            list.append(i)
             
    return list
