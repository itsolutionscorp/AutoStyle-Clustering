def sieve(limit):

    mark_list = {n: True for n in range(2, limit+1)}
    
    for i in mark_list.keys():
        if mark_list[i] == False: continue
        base = 2
        while True:
            multiple = i*base
            if multiple > limit: break
            mark_list[multiple] = False
            base += 1
               
    return [x for x in mark_list.keys() if mark_list[x] == True]
