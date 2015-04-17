import string
def word_count(phrase):
    phrase = phrase.translate(string.maketrans('',''), string.punctuation)
    wordlist = phrase.lower().split()
    wordfreq = [wordlist.count(p) for p in wordlist]
    dictionary = dict(zip(wordlist,wordfreq))
    return dictionary
