def sum_of_multiples(up_to, div=[3,5]):
    summe = 0
    if 0 in div:
        div.remove(0)
    for i in range(up_to):
            if sum([not i%x for x in div]):
                summe = summe + i
    return summe
