def detect_anagrams(word, word_list):
    word = word.lower()
    chars = sorted(list(word))
    return [anagram for anagram in word_list
                if anagram.lower() != word and sorted(list(anagram.lower())) == chars]
