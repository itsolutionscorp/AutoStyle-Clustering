def smallest_palindrome(max_factor, min_factor=0):
    pal = []
    for f1 in range(min_factor, max_factor+1):
        for f2 in range(min_factor, max_factor+1):
            n = f1*f2
            if str(n) == str(n)[::-1]:
                pal.append((n,(f1,f2)))
    return min(pal, key=lambda x:x[0])

def largest_palindrome(max_factor, min_factor=0):
    pal = []
    for f1 in range(max_factor, min_factor-1,-1):
        for f2 in range(max_factor, min_factor-1,-1):
            n = f1*f2
            if str(n) == str(n)[::-1]:
                pal.append((n,(f1,f2)))
    return max(pal, key=lambda x:x[0])
