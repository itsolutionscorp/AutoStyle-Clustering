import re
def word_count(phrase):
    dictionary = dict()
    for word in re.findall(r"[\w']+", phrase):
        word = word.lower()
        if word in dictionary:
            dictionary[word] = dictionary[word] + 1
        else:
            dictionary[word] = 1
    return dictionary
