def parse_binary(num):
    try:
        int(num, 2)
    except:
        raise ValueError
    lst = []
    n = 1
    for i in num[::-1]:
        if i == '1':
            lst.append(2**(n-1))
        n += 1
    return sum(lst)
