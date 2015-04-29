def smallest_palindrome(max_factor=1, min_factor=1):
    smallest = max_factor**2
    min_temp = min_factor
    while min_temp**2 < smallest:
        for i in xrange(min_temp,max_factor+1):
            prod = min_temp*i
            if str(prod) == str(prod)[::-1] and prod < smallest:
                smallest = prod
                factors = [min_temp,i]
                break
        min_temp += 1
    return (smallest, factors)

def largest_palindrome(max_factor=1, min_factor=1):
    largest = min_factor**2
    max_temp = max_factor
    while max_temp**2 > largest:
        for i in xrange(max_temp,min_factor-1,-1):
            prod = max_temp*i
            if str(prod) == str(prod)[::-1] and prod > largest:
                largest = prod
                factors = [max_temp,i]
                break
        max_temp -= 1
    return (largest, factors)
