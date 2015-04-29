def sieve(n):
    
    lst = range(2,n+1)
    lst_set = set(lst)
    
    for i in lst[:len(lst)/2]:
        lst_set -= set(lst[2*i-2::i])
    
    return list(lst_set)
