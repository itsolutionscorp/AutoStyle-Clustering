def slices(string, length):
    numberslist = [int(x) for x in string]
    if not 1 <= length <= len(numberslist):
        raise ValueError("Slice length issue")

    return [numberslist[i:i + length] for i in range(len(numberslist)
        - length + 1)]

def product(l):
    result = l[0]
    for x in l[1:]:
        result *= x
    return result

def largest_product(string, length):
    if len(string) == 0:
        return 1
    s = slices(string, length)
    return product(max(s, key=product))

