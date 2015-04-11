__author__ = 'tracyrohlin'

def detect_anagrams(word, string):
    anagram_words = []
    list_of_anagrams = []
    if len(string) > 1:
        for item in string:
            if len(item) == len(word):
                item, word = item.lower(), word.lower()
                if item != word:
                    for letter in list(item):
                        if letter in word:
                            anagram_words.append(letter)
                        if len(anagram_words) == len(word):
                            list_of_anagrams.append(item)
                            anagram_words = []
        return list_of_anagrams
    else:
        return anagram_words


print detect_anagrams('listen', 'enlists google inlets banana'.split())
