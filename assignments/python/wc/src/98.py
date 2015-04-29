def word_count(phrase):
    answer = {}
    words_ident = phrase.split()
    for word in words_ident:
        no = words_ident.count(word)
        answer[word] = no
    return answer
