'''def sum_of_multiples(number, list = [3,5]):
    multiples =[]
    for i in range(number):
        for y in list:
            if y!=0 and i%y==0:
                multiples.append(i)
                break

    return sum(multiples)'''

def sum_of_multiples(number, list = [3,5]):
    return sum({i:0 for i in range(number) for y in list if y!=0 and i%y==0}.keys())
