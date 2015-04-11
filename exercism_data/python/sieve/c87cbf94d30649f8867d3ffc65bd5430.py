def sieve(limit):

    limit_list = range(2, limit+1)
    mark_list = [[n, True] for n in limit_list]
    
    m = 2
    for i in limit_list:
        multiplier = [j*m for j in limit_list]
        for item in mark_list:
            if item[0] in multiplier:
                item[1] = False
            else:
                pass
        m += 1
               
    return [x[0] for x in mark_list if x[1] == True]
