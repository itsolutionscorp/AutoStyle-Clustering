def sum_of_multiples(num, factors=[3, 5]):
    multiples = []
    for i in factors:
        if not i == 0:
            for j in range(0, num, i):
                if not j in multiples:
                    multiples.append(j)
    return sum(multiples)
    
if __name__ == '__main__':
    print sum_of_multiples(10, [2,3])
