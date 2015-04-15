def detect_anagrams(word, word_list):
    return [anagram for anagram in word_list
            if  word.lower() != anagram.lower() and \
                sorted(word.lower()) == sorted(anagram.lower())]
