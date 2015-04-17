def word_count(src):
    result = {}
    _src = src.replace('\n', ' ')
    for w in _src.split(" "):
        if w == '':
            continue
        if w in result:
            result[w] += 1
        else:
            result[w] = 1
    return result
