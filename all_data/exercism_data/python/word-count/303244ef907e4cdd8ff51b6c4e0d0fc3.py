import re

def word_count(phrase):

    word_count = {}
    phrase = str(phrase)

    if not phrase:
        return word_count
    
    # removing multiple spaces
    phrase = re.sub(' +', ' ', phrase)
    # spliting with delimiters: space, newline
    word_list = re.split(' |\n', phrase)

    for word in word_list:
        word_count.setdefault(word, 0)

        if word in word_count.keys():
            word_count[word] += 1

    return word_count
