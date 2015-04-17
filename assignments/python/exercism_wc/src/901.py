import string
def word_count(words):
    for letter in words:
        if letter in string.punctuation:
            words = words.replace(letter, '')
    word_list = words.lower().split(' ')
    word_counter = {}
    for word in word_list:
        if word is not '':
            if word in word_counter:
                word_counter[word] += 1
            else:
                word_counter[word] = 1
    return word_counter
