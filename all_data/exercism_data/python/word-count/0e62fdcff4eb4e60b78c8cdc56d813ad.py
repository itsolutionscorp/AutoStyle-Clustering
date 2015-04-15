
def word_count(text):
    # declare a new dictionary
    result = {}
    # split the text
    text = text.split()
    # go through every word in dictionary
    for word in text:
        # add to new dictionary word it's occurrence
        result[word] = text.count(word)
    # return the result
    return result
