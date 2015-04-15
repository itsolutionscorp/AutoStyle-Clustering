def smallest_palindrome(max_factor, min_factor = 0):
    return find_palindrome(max_factor, min_factor)

def largest_palindrome(max_factor, min_factor = 0):
    return find_palindrome(max_factor, min_factor, first = False)

def find_palindrome(max_factor, min_factor, first = True):
    mx = fi = fj = 0

    for i in range(min_factor, max_factor + 1):
        for j in range(i, max_factor + 1):
            if is_pal(i * j) and i * j > mx:
                mx = i * j
                f1 = i
                f2 = j
                if first:
                    return (mx, {f1, f2})

    return (mx, {f1, f2})

def is_pal(num):
    s = str(num)
    end = len(s) - 1

    for i in range((len(s) + 1) / 2):
        if s[i] != s[end - i]:
            return False

    return True
