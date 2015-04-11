from collections import defaultdict
def word_count(words):
    return_dictionary = defaultdict(int)
    words_array = words.split();
    for word in words_array:
        return_dictionary[word] += 1
    return return_dictionary
