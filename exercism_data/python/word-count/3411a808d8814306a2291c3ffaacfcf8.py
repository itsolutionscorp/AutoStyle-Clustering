def word_count(words):
    result = {}
    for word in words.split():
        if not word in '':
            if word not in result.keys():
                result[word] = 1
            else:
                result[word] += 1
        
    return result
