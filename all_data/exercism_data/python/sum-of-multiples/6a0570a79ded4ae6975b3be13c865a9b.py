def sum_of_multiples(maxnum, numbers=[3,5]):
    som = 0
    for number in numbers:
        counter = 1
        multiple = number
        while (0 < multiple < maxnum):
            som += multiple
            counter += 1
            multiple = number * counter
    return som
