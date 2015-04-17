def word_count(input):
    result = {}
    no_lines_input = input.replace('\n',' ')
    words = no_lines_input.split(' ')
    for word in words:
        if len(word) == 0:
            continue
        if word in result:
            result[word] += 1
        else:
            result[word] = 1
    return result
