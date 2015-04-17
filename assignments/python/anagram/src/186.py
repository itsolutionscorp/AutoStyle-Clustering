def detect_anagrams(word, wordlist):
    # Change word to lowercase and sort characters
    word = word.lower()
    word_sorted = ''.join(sorted(word))
    anagrams = []
    for w in wordlist:
        # Change potential anagram to lowercase and sort characters
        w_low = w.lower()
        w_sorted = ''.join(sorted(w_low))
        # Anagram has the same characters as the word, but is not the word
        if w_sorted == word_sorted and w_low != word:
            anagrams.append(w)
    return(anagrams)
