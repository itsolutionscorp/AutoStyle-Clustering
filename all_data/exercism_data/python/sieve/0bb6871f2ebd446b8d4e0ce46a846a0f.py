def sieve(n):
    s = range(2,n+1)
    for i in range(len(s)):
        if type(s[i]) == str: continue
        for j in range(i+1,len(s)):
            if type(s[j]) != str:
                if s[j] % s[i] == 0: s[j] = str(s[j])
    return [item for item in s if type(item) != str]
