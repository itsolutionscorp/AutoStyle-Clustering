def numeral(n):
    r = {1000: 'M',
         100: 'CD',
         10: 'XL',
         1: 'IV'}
    if n>=1000:
        return "M"*(n/1000)+numeral(n%1000)

    for p in [10**i for i in [3,2,1,0]]:
        if n>=9*p:
            return r[p][0]+r[p*10][0]+numeral(n-9*p)
        if n>=5*p:
            return r[p][1]+numeral(n-5*p)
        if n>=4*p:
            return r[p]+numeral(n-4*p)
        if n>=p:
            return  r[p][0]*(n/p)+numeral(n%p)
    else:
        return ""
