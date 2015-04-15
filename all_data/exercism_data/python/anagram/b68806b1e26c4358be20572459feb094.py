def detect_anagrams(word, candidates):
    def _transform(word):
        return ''.join(sorted(word.lower()))

    return [anagram for anagram in candidates
            if _transform(word) == _transform(anagram)
            and anagram.lower() != word.lower()]
