def word_count(message):
    words=message.split()
    return {word:words.count(word) for word in words}
