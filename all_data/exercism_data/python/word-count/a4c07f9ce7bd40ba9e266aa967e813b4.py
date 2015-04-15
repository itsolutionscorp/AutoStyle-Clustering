def word_count(input):
    result = {}
    for x in input.split():
        result[x] = result.get(x,0) + 1
    return result
