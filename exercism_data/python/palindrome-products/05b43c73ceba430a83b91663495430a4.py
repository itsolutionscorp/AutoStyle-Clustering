'''exer palindrome products'''

def smallest_palindrome(max_factor, min_factor=1):
    '''return the smallest palindrome'''
    for i in xrange(min_factor, max_factor + 1):
        for j in xrange(i, max_factor + 1):
            product = i * j
            if is_palindrome(product):
                return (product, (i, j))

def largest_palindrome(max_factor, min_factor=1):
    '''return the largest palindrome'''
    largest = (0, (0, 0))

    for i in xrange(max_factor + 1, min_factor, -1):
        for j in xrange(i, min_factor, -1):
            this = (i*j, (i, j))
            # 8x improvement, only check those products > largest
            if this > largest:
                if is_palindrome(this[0]):  # expensive op
                    largest = this
    return largest


def is_palindrome(num):
    '''return if num reversed is equal to num'''
    return str(num) == str(num)[::-1]
