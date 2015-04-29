def detect_anagrams(given, potential):
    anagrams = []
    given_chars = sorted(given.lower())
    for match in potential:
        if len(given) != len(match) or given.lower() == match.lower():
            continue
        match_chars = sorted(match.lower())
        if given_chars == match_chars:
            anagrams.append(match)
    return anagrams
if __name__ == '__main__':
    detect_anagram('galea', ['eagle'])
