def detect_anagrams(word, phrase):
    anagrams = []
    word = word.lower()
    ordered_word = ''.join(sorted(word))
    for i in range(len(phrase)):
        lowercase_candidate = phrase[i].lower()
        ordered_candidate = ''.join(sorted(lowercase_candidate))
        if ordered_word == ordered_candidate and not word == lowercase_candidate:
            anagrams.append(phrase[i])
    return anagrams
