def sieve(n):
    numbers = {i: True for i in range(2, n + 1)}
    for prime in range(2, int(n**0.5) + 1):
        if not numbers[prime]:
            continue
        for multiple in range(prime**2, n + 1, prime):
            numbers[multiple] = False
    return [prime for prime in numbers if numbers[prime]]
