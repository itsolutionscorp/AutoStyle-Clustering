
#Would love to know how I can improve the flow
# of this function.  It looks very chaotic & I feel
# it uses more conditionals than an individual should be
# forced to keep up with in such a simple program.

def sieve(endpoint):
    top_prime = 0
    if endpoint < 2:
        prime_list=[]
    else:
        prime_list = [2]
        for number in range(endpoint+1):
            if number<2:
                pass
            else:
                for prime in prime_list:
                    if number%prime == 0:
                        break
                    else: top_prime = prime
            if top_prime==max(prime_list):
                prime_list.append(number)
            else: pass
    return prime_list  

