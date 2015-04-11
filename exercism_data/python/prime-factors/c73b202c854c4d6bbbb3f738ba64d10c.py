def prime_factors(what):
    if what==1:
        return []
    ls = []
    tmp=2
    while 1:
        for i in range(tmp,what+1):
            if what/(i) == what//(i):
                what = what//(i)
                ls.append(i)
                tmp = i
                break
        if what==1:
            break
    return ls
