def sum_of_multiples(value, multiples=[3,5]):
    multiples_list = []
    for multiple in multiples:
        if multiple != 0:
            multiples_list += [x for x in range(multiple,value,multiple)]

    #set removes duplicates from the list of multiples
    return sum(set(multiples_list))
