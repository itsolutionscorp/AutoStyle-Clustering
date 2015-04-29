def sieve(num):
    #creates a list of numbers
    sieve = [digit for digit  in xrange(2,num + 1)]

    primes = []

    working_number = sieve.pop(0)
    primes.append(working_number)
   
    #goes over every mutiple of the working_number and
    #changes it to none later removing it
    while len(sieve) > 0:
        for i, number in enumerate(sieve):
                if number % working_number == 0:
                            sieve[i] = None
        clean(sieve)

        #gets new number
        working_number = sieve.pop(0)
        primes.append(working_number)
    
    return primes 
    
def clean(dirty_list):
    """removes all None from list"""
    while None in dirty_list:
        dirty_list.remove(None)
    

    
