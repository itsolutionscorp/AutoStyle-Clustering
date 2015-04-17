def detect_anagrams(word, anagram_candidates):
    sorted_word = sorted(word.lower())
    return [
        x for x in anagram_candidates if
          sorted(x.lower()) == sorted_word and
          x.lower() != word.lower()]
