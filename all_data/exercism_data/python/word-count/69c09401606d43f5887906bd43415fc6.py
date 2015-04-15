def word_count(str):
    str_split = str.split( )
    result = {}
    for word in str_split:
        if word not in result:
            result[word] = 1
        else:
            result[word] = result[word] + 1

    return result
