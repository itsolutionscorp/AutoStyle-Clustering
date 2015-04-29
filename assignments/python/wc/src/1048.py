import string
def word_count(phrase):
    occurances = {}
    splitPhrase = (
        phrase.lower()
        .translate(string.maketrans("",""), string.punctuation)
        .split()
    )
    for word in splitPhrase:
        if not word in occurances:
            occurances[word] = splitPhrase.count(word)
    return occurances
