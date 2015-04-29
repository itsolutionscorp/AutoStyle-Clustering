from collections import Counter

def detect_anagrams(word, candidates):
    word = word.lower()
    wordcount = Counter(word)
    correctwords = []
    for possibleword in candidates:
        possiblewordcopy = possibleword.lower()
        possiblecount = Counter(possiblewordcopy)
        if list(possiblecount.elements()) == list(wordcount.elements()):
            if possibleword.lower() != word:
                correctwords.append(possibleword)
    return correctwords
