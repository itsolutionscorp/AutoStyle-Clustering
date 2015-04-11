def is_anagram(word, match):
    word = word.lower()
    match = match.lower()
    if word != match:
        return sorted(word) == sorted(match)


def detect_anagrams(word, match_list):
    return [i for i in match_list if is_anagram(word, i)]
