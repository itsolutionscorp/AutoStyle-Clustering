def word_count(text):

    # create empty dict
    result = {}

    # go through each word in text split by space
    for word in text.split():
        # If the word is in the dictionary add one to the entry
        if word in result.keys():
            result[word] += 1
        # If the word is not in the dict add it with a value of one
        else:
            result[word] = 1
    return result
        
