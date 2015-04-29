def set_starting_conditions(end):
    '''
    creates array, marks known not primes w/ 0
    possible primes with 1 
    '''
    sieve = [1 for num in range(0,end+1)] 
    sieve[0] = 0  
    sieve[1] = 0
    return sieve


def clear_field(field):
    '''
    identifies the next prime and marks multiples with 0
    '''
    for pos,val in enumerate(field): 
        if val == 1:
            for mult in range(2,len(field)/2):
                if pos*mult > len(field) - 1 :
                    pass
                else:
                    field[pos*mult] = 0
        elif val == 0: 
            pass
    return field


def sieve(end):
    '''
    main function
    ''' 
    sieve = set_starting_conditions(end)
    sieve = clear_field(sieve)

    primes = []
    for pos, val in enumerate(sieve):
        if val == 1:
            primes.append(pos)
    return primes



if __name__ == '__main__':
    print sieve(7)
    # print clear_field([0,0,1,1,1,1])
