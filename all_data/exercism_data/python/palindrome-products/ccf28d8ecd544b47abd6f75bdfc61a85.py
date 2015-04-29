def get_palindromes(max_factor, min_factor):
    lst = []
    for i in range(min_factor, max_factor+1):
        for j in range(min_factor, max_factor+1):
            result = i * j
            if str(result) == str(result)[::-1]:
                lst.append((result, set([i, j])))
    return lst

def smallest_palindrome(max_factor, min_factor=0):
    return min(get_palindromes(max_factor, min_factor))
    

def largest_palindrome(max_factor, min_factor=0):
    return max(get_palindromes(max_factor, min_factor))
