def word_count(text):
    wordlist = text.split()
    return {word: wordlist.count(word) for word in wordlist}
