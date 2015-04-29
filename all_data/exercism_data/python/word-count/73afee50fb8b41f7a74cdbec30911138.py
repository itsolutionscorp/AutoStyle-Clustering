def word_count(text):
    words = dict()
    for w in text.split():
        if not checkDictionary(w, words):
            words[w] = 1
    return words

def checkDictionary(word, words):
    for item in words:
        if item == word:
            words[item] = words[item] + 1
            return True
    return False
