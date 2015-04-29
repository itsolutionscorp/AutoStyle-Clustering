def detect_anagrams(word, wordlist):
    final = []
    for words in wordlist:
        if word.lower() != words.lower() and sorted(word.lower()) == sorted(words.lower()):
            final.append(words)
    return final
