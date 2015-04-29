__author__ = 'nebur1989'


def detect_anagrams(word, anagrams):
    word_lower = word.lower()
    detected_anagrams = []
    word_characters = sorted(list(word_lower))
    for anagram in anagrams:
        anagram_lower = anagram.lower()
        if anagram_lower != word_lower:
            anagram_characters = sorted(list(anagram_lower))
            if word_characters == anagram_characters:
                detected_anagrams.append(anagram)
    return detected_anagrams
