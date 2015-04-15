def smallest_palindrome(max_factor, min_factor=0):
    cur_min = max_factor**2 + 1
    for f1 in range(min_factor, max_factor + 1):
        for f2 in range(f1, max_factor + 1):
            if f1**2 > cur_min:
                break  # don't waste time on factors that won't be minimums
            product = f1*f2
            if is_palindrome(product) and product < cur_min:
                cur_min = product
                cur_f1 = f1
                cur_f2 = f2
    if cur_min > max_factor**2:
        return ()
    else:
        return (cur_min, (cur_f1, cur_f2))

def largest_palindrome(max_factor, min_factor=0):
    cur_max = 0
    for f1 in range(max_factor, min_factor - 1, -1):
        for f2 in range(f1, min_factor - 1, -1):
            if f1**2 < cur_max:
                break  # don't waste time on factors that won't be maximums
            product = f1*f2
            if is_palindrome(product) and product > cur_max:
                cur_max = product
                cur_f1 = f1
                cur_f2 = f2
    if cur_max == 0:
        return ()
    else:
        return (cur_max, (cur_f1, cur_f2))

def is_palindrome(num):
    digits = list(str(num))
    while len(digits) > 1:
        if digits.pop(0) != digits.pop():
            return False
    return True
