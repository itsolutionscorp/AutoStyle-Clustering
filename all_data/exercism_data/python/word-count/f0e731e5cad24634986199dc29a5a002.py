def word_count(parse):
    count = {}
    for item in parse.split():
        if item not in count:
            count[item] = 1
        else:
            count[item] += 1
    return count
