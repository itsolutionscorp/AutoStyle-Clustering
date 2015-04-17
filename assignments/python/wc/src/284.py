def word_count(text):
    wordlist = text.split()
    dictionary = {word: wordlist.count(word) for word in wordlist}
    return dictionary
