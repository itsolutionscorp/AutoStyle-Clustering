def detect_anagrams(word, anagram_list):
    matches = []
    word = word.lower()
    for item in anagram_list:
        if not item.lower() == word.lower():
            if sorted(item.lower()) == sorted(word):
                matches.append(item)
    return matches
