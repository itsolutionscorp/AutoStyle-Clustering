
def sieve(limit):
    out = []
    for i in range(2, limit + 1):
        for j in range(2, limit):
            if i % j == 0:
                out.append(j)
                break

    return sorted(list(set(out)))
