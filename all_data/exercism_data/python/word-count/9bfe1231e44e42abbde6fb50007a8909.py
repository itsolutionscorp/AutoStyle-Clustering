def word_count(text):

    result = {}
    split_text=text.split()
    for word in split_text:
        if word in result.keys():
            result[word] += 1
        else:
            result[word] = 1
    return result
        
