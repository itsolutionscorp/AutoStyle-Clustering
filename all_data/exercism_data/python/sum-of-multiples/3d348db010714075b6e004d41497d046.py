def sum_of_multiples(whole,set_multiples=[3,5]):
    total_sum = [0]
    for i in range(0,whole):
        for n in set_multiples:
            try:
                if i%n == 0:
                    if i not in total_sum:
                        total_sum.append(i)
            except ZeroDivisionError:
                next
    return sum(total_sum)
