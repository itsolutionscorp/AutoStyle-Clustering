'''
    for every number from 1 to limit
        for every factor in factors
            if the number is a multiple of any factor
                add number to total

    alternate was
    sum every multiple of every factor
    subtract multiple of the product of factors
        example
            sum(i for i in range(3,1000,3) + sum(i for i in range(5,1000,5) - sum(i for i in range(3*5,1000,3*5)
    this works well if there is a max of 2 factors, but becomes complicated for more than 2 factors
            example
                sum(7's) + sum(13's) + sum(5's) - sum(7*13) - sum(7*5) - sum(13*5) + sum(7+13*5)
        
        
        
'''

def sum_of_multiples(limit, factors=[3, 5]):
    return sum(i for i in range(limit) if any(f != 0 and i % f == 0 for f in factors))

    
    
