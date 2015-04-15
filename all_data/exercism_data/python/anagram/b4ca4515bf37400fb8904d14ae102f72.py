def is_anagram(word, candidate):
    word, candidate = word.lower(), candidate.lower()
    if word == candidate:
        return False
    if len(word) > len(candidate):
        return False
    for c in candidate:
        if c not in word:
            return False
        if word.count(c) < candidate.count(c):
            return False
    return True

def detect_anagrams(word, candidates):
    return [c for c in candidates if is_anagram(word, c)]


if __name__ == '__main__':
    print detect_anagrams('listen', ['enlists', 'google', 'inlets', 'banana'])
