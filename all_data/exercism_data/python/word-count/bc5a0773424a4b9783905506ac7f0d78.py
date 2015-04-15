def word_count(phrase):
    final = {}
    for word in phrase.split():
        final[word] = final.get(word, 0) +1
    return final
