'''
Given a phrase, count the occurrences of each word in that phrase.
Return a dictionary of the count
'''
def word_count(phrase):
    words = phrase.split()
    words_dict = {}
    for word in words:
        if not words_dict.get(word):
            words_dict.update({word:1})
        else:
            n = words_dict.get(word) + 1
            words_dict[word] = n
    return words_dict
