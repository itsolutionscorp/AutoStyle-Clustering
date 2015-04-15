# Sum Of Multiples

def sum_of_multiples(max_num, factors = [3, 5]):
    multiples = set()
    
    for factor in factors:
        if factor > 0:
            multiples = multiples | set(range(factor, max_num, factor))
         
    return sum(multiples)
