def sort_alphabetically(word):
    return ''.join(sorted(word.lower()))


def detect_anagrams(word, candidates):
    sorted_word = sort_alphabetically(word)
    anagrams = []
    for potential_anagram in candidates:
        if (sorted_word == sort_alphabetically(potential_anagram)) and (word.lower() != potential_anagram.lower()):
            anagrams.append(potential_anagram)

    return anagrams
