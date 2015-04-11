def sieve(limit):
    possibles = range(2, limit+1)
    marked = []

    for possible_prime in possibles:
        if possible_prime not in marked:
            for multiplier in range(2, (limit/possible_prime)+1):
                if possible_prime * multiplier in possibles:
                    marked.append(possible_prime*multiplier)
                    possibles.remove(possible_prime*multiplier)
    return possibles
