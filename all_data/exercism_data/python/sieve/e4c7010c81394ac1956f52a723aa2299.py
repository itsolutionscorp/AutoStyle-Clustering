def sieve(n):
    l = range(2, n+1)

    counter = 0
    while counter < len(l):
        marker = l[counter]
        for r in l[counter+1:]:
            if r % marker == 0:
                l.remove(r)
        counter += 1
    return l

if __name__ == '__main__':
    print sieve(10)
    print sieve(1000)
