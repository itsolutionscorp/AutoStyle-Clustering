def distance(one, two):
    count = 0 
    nucleos = len(one)
    for nucleo in range(nucleos):
        if one[nucleo] != two[nucleo]:
            count += 1
    return count
