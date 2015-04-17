import string
table = string.maketrans("","")
def word_count(phrase):
    normalized = phrase.lower()
    word_list = normalized.translate(table, string.punctuation).split(' ')
    word_list = filter(None, word_list)
    word_map = {}
    for i in word_list:
        word = i.strip()
        if word in word_map:
            word_map[word] += 1
        else:
            word_map[word] = 1
    return word_map
