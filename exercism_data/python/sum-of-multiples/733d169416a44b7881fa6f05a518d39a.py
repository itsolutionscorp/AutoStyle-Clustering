def sum_of_multiples(number, list = [3,5]):
    multiples =[]
    for i in range(1,number):
        for y in list:
            if y!=0 and i%y==0:
                multiples.append(i)
                break

    return sum(multiples)
