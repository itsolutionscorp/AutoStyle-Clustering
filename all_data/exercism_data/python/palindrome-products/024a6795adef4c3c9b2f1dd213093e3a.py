def smallest_palindrome(max_factor, min_factor = 0):

    smallest = None

    for x in xrange(min_factor or 1, max_factor + 1):
        for y in xrange(x, max_factor + 1):
            product = x * y
            if smallest and product >= smallest[0]:
                break
            if is_palindrome(product):
                smallest = (product, {x,y})
                break

    return smallest

def largest_palindrome(max_factor, min_factor = 0):

    largest = None

    for x in xrange(max_factor, 0, -1):
        for y in xrange(max_factor, x-1, -1):
            product = x * y
            if largest and product <= largest[0]:
                break
            if is_palindrome(product):
                largest = (product, {x,y})
                break

    return largest

def is_palindrome(number):
    number_as_str = str(number)
    return number_as_str == number_as_str[::-1]
