def word_count(sentence):
    words = {}
    for word in sentence.split():
        word2 = ""
        for char in word:
            if char.isalpha() or char.isdigit():
                word2 += char.lower()
        if not word2:
            pass
        elif words.get(word2):
            words[word2] += 1
        else:
            words[word2] = 1
    return words
