def search(range1, range2, break_cond1, break_cond2, better):
    result = None
    for i in range1:
        if break_cond1(result, i):
            break
        for j in range2:
            if break_cond1(result, i) or break_cond2(result, i, j):
                break
            result = better(result, i, j)
    return result


def smallest_palindrome(max_factor, min_factor=1):
    range1 = range(min_factor, max_factor + 1)
    range2 = range(max_factor, min_factor - 1, - 1)
    break_cond1 = lambda result, i: result is not None and i * i >= result[0]
    break_cond2 = lambda result, i, j: j < i
    def better(result, i, j):
        if is_palindrome(i * j):
            if result is None or i * j < result[0]:
                return [i * j, {i, j}]
        return result
    return search(range1, range2, break_cond1, break_cond2, better)


def largest_palindrome(max_factor, min_factor=1):
    range1 = range(max_factor, min_factor - 1, -1)
    range2 = range(min_factor, max_factor + 1)
    break_cond1 = lambda result, i: result is not None and i * max_factor < result[0]
    break_cond2 = lambda result, i, j: j > i
    def better(result, i, j):
        if is_palindrome(i * j):
            if result is None or i * j > result[0]:
                return [i * j, {i, j}]
        return result
    return search(range1, range2, break_cond1, break_cond2, better)

def is_palindrome(n):
    s = str(n)
    return s == ''.join(reversed(s))
