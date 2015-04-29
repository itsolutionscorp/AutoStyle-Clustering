from operator import mul

primes = [2, 3]

def has_prime_factors(num):
    for prime in primes:
        if num % prime == 0:
            return True
    return False

def gen_first_n_primes(num):
    while len(primes) < num:
        guess = primes[-1] + 2
        while has_prime_factors(guess):
            guess += 1
        primes.append(guess)

# makes a list of primes corresponding to word
def word_to_primes_list(word):
    return [primes[ord(char.lower()) - ord('a')] for char in word]

# product of the list of primes
def primes_product(word):
    return reduce(mul, word_to_primes_list(word), 1)

def is_anagram(word, anagram):
    return primes_product(word) == primes_product(anagram)
    
def detect_anagrams(word, anagrams):
    gen_first_n_primes(26)
    i = 0
    while i < len(anagrams):
        if is_anagram(word, anagrams[i]) and word.lower() != anagrams[i].lower():
            i += 1
        else:
            anagrams.pop(i)
    return anagrams
