def word_count(phrase):
    word_list = phrase.split()
    result = {word:word_list.count(word) for word in word_list}
    return result
