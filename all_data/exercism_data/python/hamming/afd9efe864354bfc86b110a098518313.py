def distance(s, t):
    length = len(s)
    count = 0
    for x in range(0, length):
        if s[x] != t[x]:
            count+=1
    return count
