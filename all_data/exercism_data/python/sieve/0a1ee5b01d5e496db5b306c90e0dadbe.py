def sieve(limit):
    marked = set()
    result = []
    numbers = range(2, limit+1)
    for n in numbers:
        if (n not in marked):
            result.append(n)
            product = n*2
            multiplier = 2
            while (product <= limit):
                marked.add(product)
                multiplier += 1
                product = n * multiplier
    return result
