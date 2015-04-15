'''exer palindrome products'''

def smallest_palindrome(max_factor, min_factor=1):
    '''return the smallest palindrome'''
    return get_palindromes(max_factor, min_factor)[0]

def largest_palindrome(max_factor, min_factor=1):
    '''return the smallest palindrome'''
    return get_palindromes(max_factor, min_factor)[-1]


def get_palindromes(maxf, minf):
    '''return all palindromes, sorted'''
    palindromes = []
    for i in range(minf, maxf + 1):
        for j in range(i, maxf + 1):
            product = i * j
            if is_palindrome(product):
                palindromes.append((product, (i, j)))
    return sorted(palindromes, key=lambda val: val[0])

def is_palindrome(num):
    '''return if num reversed is equal to num'''
    return str(num) == str(num)[::-1]
