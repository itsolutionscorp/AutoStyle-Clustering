def sieve(max_num):
    prime_list = range(2, max_num+1)
    i = 0
    while i < len(prime_list): 
        prime_list = [ x for x in prime_list if x == prime_list[i] or x % prime_list[i] != 0 ]
        i += 1
    return prime_list

if __name__ == '__main__':
    print sieve(20)
