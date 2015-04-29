def sieve(num):
    ''' finds all primes in a range from 1 to num 
        by using the good old sieve of Eratosthenes(sp?)
    '''
    
    #initialise sieve
    composite = [];
    for i in range(2,num):
        composite.append(i) # first possible composite is 2
   
    for i in composite:
        x = 2
        while (x * i) <= num:
            if x * i in composite:
                composite.remove(x*i) 
            x += 1
            
    return composite
