def word_count(words):
    word_list = words.split()
    return {word: word_list.count(word) for word in word_list}
