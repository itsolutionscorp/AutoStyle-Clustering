def word_count(phrase):
    word_list = phrase.split()
    wordcount = dict()
    unique_words = set()
    for word in word_list:
        if word not in unique_words:
            unique_words.add(word)
            wordcount[word] = 1
        else:
            wordcount[word] += 1
    return wordcount
