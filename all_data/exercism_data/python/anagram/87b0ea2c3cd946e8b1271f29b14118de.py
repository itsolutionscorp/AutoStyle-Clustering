from collections import Counter

def detect_anagrams(word,anagram_candidates):
    if isinstance(word, basestring) and isinstance(anagram_candidates,list):
        result_anagrams = []
        word = word.lower()
        character_count = Counter()

        for character in word:
            character_count[character] += 1

        for candidate in anagram_candidates:
            if is_anagram(word, candidate.lower(), character_count.copy()):
                result_anagrams.append(candidate)

        return result_anagrams

def is_anagram(word,candidate,character_count):
    if len(word) == len(candidate):
        if word != candidate:
            for character in candidate:
                if character_count[character] == 0:
                    return False
                character_count[character] -= 1

            return True
