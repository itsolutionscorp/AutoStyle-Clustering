def word_count(input):
    # return value
    result = {}
    
    # clear input by removing new lines,
    # and spliting input by space
    no_lines_input = input.replace('\n',' ')
    words = no_lines_input.split(' ')
    
    # counting all words
    for word in words:
        if len(word) == 0:
            continue
        if word in result:
            result[word] += 1
        else:
            result[word] = 1

    return result
