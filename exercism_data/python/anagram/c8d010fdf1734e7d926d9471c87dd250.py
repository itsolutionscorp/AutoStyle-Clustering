__author__ = 'Dalton'

def detect_anagrams(word, word_list):
    correct = []

    for i in word_list:
        if sorted(word.lower()) == sorted(i.lower()) and word.lower() != i.lower():
            correct.append(i)

    return correct
