def detect_anagrams(word, anagram_list):
    matches = []
    for item in anagram_list:
        if not item.lower() == word.lower():
            if sorted(item.lower()) == sorted(word.lower()):
                matches.append(item)
    return matches
