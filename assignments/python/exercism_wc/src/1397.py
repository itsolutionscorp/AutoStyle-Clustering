def word_count(words):
    return {word: words.split().count(word) for word in words.split()}
