def distance(s, t):
    num = 0
    for x in range(0, len(s)):
        if (s[x] != t[x]):
            num += 1
    return num
