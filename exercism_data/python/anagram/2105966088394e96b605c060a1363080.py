import operator

primes = [ 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
          31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
          73, 79, 83, 89, 97, 101, 103, 107, 109 ]

chars = [chr(i) for i in range(ord('a'), ord('z') + 1)]

# calculate map from chars -> prime numbers
# 'a' -> 2, 'b' -> 3, ..., 'z' -> 101
anmap = dict(zip(chars, primes))

def prime(w):
    # product of prime factors is always the same regardless of the order
    # so anagrams will yield the same prime number ;)
    return reduce(operator.mul, [anmap.get(c, 103) for c in w])

def detect_anagrams(word, word_list):
    prime_w = prime(word.lower())
    return filter(lambda x: word.lower() != x.lower() and prime_w == prime(x.lower()), word_list)
