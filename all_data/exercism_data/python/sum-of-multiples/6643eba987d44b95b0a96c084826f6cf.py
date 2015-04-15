def sum_of_multiples(n, multiples=[3, 5]):
    result = 0

    for i in range(1, n):
        if isMultiple(i, multiples):
            result += i

    return result

def isMultiple(n, multiples):
    isMultiple = False

    for multiple in multiples:
        if multiple > 0 and n % multiple == 0:
            isMultiple = True

    return isMultiple
