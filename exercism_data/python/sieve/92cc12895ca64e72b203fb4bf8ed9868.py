def sieve(n):
    s = [1] * (n + 1)
    s[0] = s[1] = 0
    i = 2
    while i <= n:
        if s[i] == 1:
            j = 2 * i
            while j <= n:
                s[j] = 0
                print(j, s)
                j += i

        i += 1

    print(s)

    return [i for i in range(n + 1) if s[i]]
