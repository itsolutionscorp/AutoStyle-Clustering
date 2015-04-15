def detect_anagrams(word, list_of_words):

    return [anagram for anagram in list_of_words
            if len(word) == len(anagram)
            and sorted(list(word.lower())) == sorted(list(anagram.lower()))
            and word.lower() != anagram.lower()
            ]
