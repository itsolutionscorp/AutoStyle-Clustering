from itertools import permutations

def _is_even(number):
    return number % 2 == 0

def _is_palindrome(input):
    textinput = str(input)
    inputN = len(textinput)
    if inputN == 1:
        return True
        
    left = textinput[0:inputN/2]
    offset = 1 if _is_even(inputN) else 0
    right = textinput[-1:inputN/2-offset:-1] 
    return left == right
    
def largest_palindrome(max_factor, min_factor = 0):
    largestvalue = 0
    factor = set()
    for i in range(max_factor, min_factor - 1, -1):
        for j in range(i, min_factor -1 , -1):
            product = i * j
            if product < largestvalue:
                break
            if _is_palindrome(product):
                if product > largestvalue:
                    largestvalue = product
                    factor = set([i,j])

    return (largestvalue, factor)
    
def smallest_palindrome(max_factor, min_factor=0):
    smallestvalue = pow(max_factor,2)
    factor = []
    for i in range(min_factor, max_factor + 1):
        for j in range(i, max_factor + 1):
            product = i * j
            if product > smallestvalue:
                break
            if _is_palindrome(product):
                if product < smallestvalue:
                    smallestvalue = product
                    factor = [i,j]
                    
    return (smallestvalue, factor)
