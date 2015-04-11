def prime_factors(n):
    primfac = []
    d = 2
    while d*d <= n:
        while (n % d) == 0:
            primfac.append(d)
            n /= d
        d += 1
    if n > 1:
       primfac.append(n)
    return primfac


"""
    current_num=2
    pf=[]
    while current_num<num:
        if num%current_num==0:
            counter=current_num-1
            test=True
            while counter>1:
                if current_num%counter==0:
                    test=False
                counter=counter-1
            if test:
                pf.append(current_num)
        current_num=current_num+1
    pf=list(set(pf))
"""
                        
                
