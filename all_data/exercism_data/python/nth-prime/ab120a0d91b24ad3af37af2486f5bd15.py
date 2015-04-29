def nth_prime(nth):
    num = 1
    result = []
    while nth > 0: #while primes remain unfound, complete the following:
        num += 1
        print(num)
        if num == 2:
            nth -= 1
            continue
        for i in range(2,num):
            if num % i == 0:
                break
            elif not num == i+1:
                continue
            nth-= 1
    return num
