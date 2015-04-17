def detect_anagrams(seed, comparisons):
        anagrams = [word for word in comparisons
                    if sorted(word.lower()) == sorted(seed.lower())
                    and word.lower()!=seed.lower()]
        return anagrams
