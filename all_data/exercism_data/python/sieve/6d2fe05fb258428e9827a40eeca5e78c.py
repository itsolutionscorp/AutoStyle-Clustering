

def sieve(n):
    unmarked = range(2, n + 1)
    marked = []
    for p in unmarked:
        if p in marked:
            continue
        for x in unmarked:
            comp_number = p * x
            if comp_number <= n:
                marked.append(comp_number)      
    return [prime for prime in unmarked if prime not in marked]

