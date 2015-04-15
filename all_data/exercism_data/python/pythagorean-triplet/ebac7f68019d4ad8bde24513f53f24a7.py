import cProfile
def primitive_triplets(b):
    if b%4!=0:
        raise ValueError
    ls = []
    pows = {}
    #b = 2mn
    #range for m,n is b/2
    mnrange = b//2+1
    for m in range(mnrange):
        for n in range(mnrange):
            if (m-n)%2==1 and m>n:
                if gcd(m,n)==1:
                    check([m,n],pows)
                    a,c = pows[m]-pows[n],pows[m]+pows[n]
                    check([a,b,c],pows)
                    if pows[a] + pows[b] == pows[c]:
                        if a<b:
                            ls.append((a,b,c))
                        else:
                            ls.append((b,a,c))
    return set((ls))

def check(ints,dict):
    for i in ints:
        if i not in dict.keys():
            dict[i] = pow(i,2)

def gcd(a,b):
        """ the euclidean algorithm """
        while a:
                a, b = b%a, a
        return b

def triplets_in_range(lowerlim,upperlim):
    results = []
    dict_of_pows = {}
    for i in range(lowerlim,upperlim+1):
        dict_of_pows[i] = i**2
    for a in range(lowerlim,upperlim+1):
        for b in range(lowerlim,upperlim+1):
            for c in range(lowerlim,upperlim+1):
                if a<b<c:
                    if dict_of_pows[a]+dict_of_pows[b]==dict_of_pows[c]:
                        results.append((a,b,c))
    return set(results)

def is_triplet(what):
    what = list(what)
    what.sort()
    return what[0]**2 + what[1]**2 == what[2]**2
        
