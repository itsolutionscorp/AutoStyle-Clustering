__author__ = 'Hinek'
def slices(string, length):
    if length < 1 or length > len(string):
        raise ValueError
    result = []
    for i in range(len(string) - length + 1):
        result.append([int(i) for i in list(string[i:i+length])])
    return result
