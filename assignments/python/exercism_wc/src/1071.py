def word_count(text):
    word_list = text.split()
    count = {}
    for word in word_list:
        count[word] = count.get(word, 0) + 1
    return count
