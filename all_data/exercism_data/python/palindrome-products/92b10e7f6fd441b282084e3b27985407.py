def smallest_palindrome(max_factor, min_factor = 1):
    def generate_x(): return range(min_factor, max_factor+1)
    def generate_y(x): return range(x, max_factor + 1)
    return _find_palindrome(generate_x, generate_y, min)

def largest_palindrome(max_factor, min_factor = 1):
    def generate_x(): return range(max_factor, min_factor-1, -1)
    def generate_y(x): return range(max_factor, x-1, -1)
    return _find_palindrome(generate_x, generate_y, max)

def _find_palindrome(generate_x, generate_y, pick_func):
    palindrome = None
    for x in generate_x():
        for y in generate_y(x):
            product = x * y
            if palindrome and pick_func(palindrome[0], product) != product:
                break
            if _is_palindrome(product):
                palindrome = (product, {x,y})
                break
    return palindrome 

def _is_palindrome(number):
    number_as_str = str(number)
    return number_as_str == number_as_str[::-1]
