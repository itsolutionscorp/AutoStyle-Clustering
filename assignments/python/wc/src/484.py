import re
def word_count(phrase):
    phrase = re.sub('[^A-Za-z1-9 ]', '', phrase)
    word_dict = {}
    tokens = phrase.strip().split()
    for word in tokens:
        word_dict[word.lower()] = word_dict.get(word.lower(), 0) + 1
    return word_dict
