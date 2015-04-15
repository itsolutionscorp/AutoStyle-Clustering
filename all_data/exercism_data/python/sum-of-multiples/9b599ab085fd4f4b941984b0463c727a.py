def sum_of_multiples(num, mul_list=[3, 5]):
    add_list = [0]
    for x in range(1, num):
        for mul_num in mul_list:
            if mul_num == 0:
                continue
            if x % mul_num == 0:
                add_list.append(x)
                break
    return sum(add_list,0)
