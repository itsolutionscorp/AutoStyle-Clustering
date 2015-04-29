def is_anagram(word, candidate):
    word, candidate = word.lower(), candidate.lower()
    if word == candidate:
        return False
    return (sorted(word) == sorted(candidate))

def detect_anagrams(word, candidates):
    return [c for c in candidates if is_anagram(word, c)]


if __name__ == '__main__':
    print detect_anagrams('listen', ['enlists', 'google', 'inlets', 'banana'])
