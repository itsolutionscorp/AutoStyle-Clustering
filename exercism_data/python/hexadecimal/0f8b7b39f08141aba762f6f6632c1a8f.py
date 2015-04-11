def hexa(s):
    d = {'a':10,
         'b':11,
         'c':12,
         'd':13,
         'e':14,
         'f':15,}
    for i in range(10): d[str(i)] = i
    
    s = s.lower()
    
    if len(s) != sum([s.count(key) for key in d]):
        raise ValueError('Incorect input for string representation of hexadecimal.')
    
    return sum([d[s[-(i+1)]] * 16 ** i for i in range(len(s))])
