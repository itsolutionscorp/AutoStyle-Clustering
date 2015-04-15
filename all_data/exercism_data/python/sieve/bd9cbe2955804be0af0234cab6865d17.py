def sieve(max_num):
    prime_list = range(2, max_num+1)
    length = len(prime_list)
    i = 0
    while i < length: 
        prime_list = [ x for x in prime_list if x == prime_list[i] or x % prime_list[i] != 0 ]
        i += 1
        length = len(prime_list) 
    return prime_list

if __name__ == '__main__':
    print sieve(20)
