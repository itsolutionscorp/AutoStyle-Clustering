def detect_anagram(word, list_of_anagrams):
    word_chars = []
    anagrams = []
    for ch in word:
        word_chars.append(ch)
    for anagram in list_of_anagrams:
        anagram_chars = []
        for ch in anagram:
            anagram_chars.append(ch)
        if sorted(word_chars) == sorted(anagram_chars):
            anagrams.append(anagram)
    return anagrams
