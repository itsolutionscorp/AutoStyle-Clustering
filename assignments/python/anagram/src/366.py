# from a given word and a lis of candidates
# find the corret anagram.
def detect_anagrams(word, list_of_words):
    return [item for item in list_of_words
            if len(word) == len(item)
            and sorted(list(word.lower())) == sorted(list(item.lower()))
            and word.lower() != item.lower()
            ]
