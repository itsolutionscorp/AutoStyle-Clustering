def word_count(string):
    words = [i for i in string.replace('\n', ' ').split(' ') if i]
    return dict([(word, words.count(word)) for word in set(words)])
