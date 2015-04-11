def smallest_palindrome(max_factor, min_factor=0):
    pal = ()
    min_pal = max_factor*max_factor + 1
    for f1 in range(min_factor, max_factor+1):
        for f2 in range(min_factor, max_factor+1):
            n = f1*f2
            if n >= min_pal:
                break
            if str(n) == str(n)[::-1]:
                min_pal = n
                pal = (n, (f1,f2))
    return pal

def largest_palindrome(max_factor, min_factor=0):
    pal = ()
    max_pal = 0
    for f1 in range(max_factor, min_factor-1,-1):
        for f2 in range(max_factor, min_factor-1,-1):
            n = f1*f2
            if n <= max_pal:
                break
            if str(n) == str(n)[::-1]:
                max_pal = n
                pal = (n, (f1,f2))
    return pal
