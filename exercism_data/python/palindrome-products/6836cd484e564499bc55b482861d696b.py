def smallest_palindrome(max_factor, min_factor=0):
    return palindrome(max_factor, min_factor, False)

def largest_palindrome(max_factor, min_factor=0):
    return palindrome(max_factor, min_factor, True)

def palindrome(max_factor, min_factor, reverse_flag):
    products = [(f1*f2, (f1, f2)) for f1 in range(min_factor, max_factor+1) for f2 in range(min_factor, f1+1)]
    sorted_prod = sorted(products, key=lambda tup: tup[0], reverse=reverse_flag)
    for tup in sorted_prod:
        if is_palindrome(tup[0]):
            return tup
    return ()

def is_palindrome(num):
    digits = list(str(num))
    while len(digits) > 1:
        if digits.pop(0) != digits.pop():
            return False
    return True
