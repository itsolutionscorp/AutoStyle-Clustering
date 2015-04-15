import re
def word_count(big_string):
    hash_word_storage = {}
    for word in big_string.replace("\n", " ").split(" "):
        if word in hash_word_storage.keys():
            hash_word_storage[word] += 1
        else:
            hash_word_storage[word] = 1
    return hash_word_storage
