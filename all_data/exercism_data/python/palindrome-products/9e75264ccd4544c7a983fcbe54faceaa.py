def smallest_palindrome(max_factor, min_factor=None):
    if min_factor == None:
        min_factor = 1
    if max_factor == None:
        raise ValueError
    value, return_set = get_min_palindrome(min_factor,max_factor)
    return value, return_set


def largest_palindrome(max_factor, min_factor=None):
    if min_factor == None:
        min_factor = 0
    if max_factor == None:
        raise ValueError
    return get_max_palindrome(min_factor, max_factor)

def get_min_palindrome(min, max):
    for first in range(min,max+1):
        for second in range(first,max+1):
            if is_palindrome(first*second):
                return_set = ({first,second})
                return first*second, return_set
    return None, None

def get_max_palindrome(min, max):
    elm = -1
    for first in range(max+1,min, -1):
        for second in range(first,min, -1):
            if is_palindrome(first*second):
                if first*second > elm:
                    elm = first*second
                    return_set = ({first,second})
                    val1 = first*second
                    val2 = return_set
    return val1, val2


def is_palindrome(value):
    forward_number = list(str(value))
    reverse_number = list(str(value))
    reverse_number.reverse()
    if str(forward_number) == str(reverse_number):
        return True
    else:
        return False
