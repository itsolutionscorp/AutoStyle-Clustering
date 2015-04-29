def sum_of_multiples(limit, multiples=[3,5]):
    
    sum_list = []
    for n in multiples:
        if n >= limit:
            break
        x = 1
        while True:
            add = n * x
            sum_list.append(add)
            x += 1
            if n*x >= limit:
                break
            elif n == 0:
                break
    set_list = set(sum_list)
    return sum(set_list)
