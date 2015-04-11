import string
def word_count(phrase):
    count = {}
    for symbol in string.punctuation:
        phrase = phrase.replace(symbol, '')
    for word in phrase.lower().split():
        if not word in count:
            count[word] = 1
        else:
            count[word] += 1
    return count
