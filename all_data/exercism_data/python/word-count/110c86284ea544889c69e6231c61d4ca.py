def word_count(input):
    words = input.split()
    result = {}
    for x in words:
        result[x] = result.get(x,0) + 1
    return result
