def detect_anagrams(word_to_match, words):
    anagrams = []
    for word in words:
        if is_anagram(word_to_match.lower(), word.lower()):
            anagrams.append(word)
    return anagrams


def is_anagram(word_to_match, word):
    if word_to_match == word:
        return False
    return ''.join(sorted(word_to_match)) == ''.join(sorted(word))
