def detect_anagrams(word, phrase):
    anagrams = []
    word = word.lower()
    for i in range(len(phrase)):
        lowercase_phrase = phrase[i].lower()
        if len(word) == len(phrase[i]) and not word == lowercase_phrase:
            anagram_test = [letter for letter in lowercase_phrase]
            for j in word:
                if j in anagram_test:
                    anagram_test.remove(j)
                else:
                    break
            if len(anagram_test) == 0:
                anagrams.append(phrase[i])
    return anagrams
