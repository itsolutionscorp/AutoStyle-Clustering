def sieve(max_num):
    sieve = { init : True for init in range(2, max_num + 1)}

    for base_num in sieve.keys():
        if sieve[base_num] == False: continue
        multiplier = 2
        while True:
            results = base_num * multiplier
            if results > max_num: break
            sieve[results] = False
            multiplier += 1

    return [prime for prime in sieve.keys() if sieve[prime] == True]
