from itertools import combinations

def slices(string, n):
    result = []
    stuff = [int(number) for number in list(string)]
    if len(stuff) < n or n == 0:
        raise ValueError('Error!')
    while len(stuff) >= n:
        list1 = []
        i = 0
        while i < n:
            list1.append(stuff[i])
            i += 1
        result.append(list1)
        stuff.pop(0)
    return result

def largest_product(string, n):
    if string == "" or n == 0:
        return 1
    
    lists = slices(string, n)
    final = []

    for item in lists:
        sum = 1
        for i in item:
            sum *= i
        final.append(sum)
    return max(final)
