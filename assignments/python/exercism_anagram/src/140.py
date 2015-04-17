# anagram.py
def detect_anagrams(word, word_list):
    '''detects anagrams of word in word_list'''
    for letter in word:
        for candidate in word_list:
            if len(candidate) != len(word):
                word_list.remove(candidate)
            elif letter.lower() not in candidate.lower():
                word_list.remove(candidate)
            elif candidate.lower() == word.lower():
                word_list.remove(candidate)
            elif sorted(candidate.lower()) != sorted(word.lower()):
                word_list.remove(candidate)
    return word_list
