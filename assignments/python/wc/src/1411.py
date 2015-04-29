def word_count(phrase):
    phrase = phrase + ' '
    phrase = phrase.replace('\n', ' ')
    print phrase
    word_list = []
    words = {}
    word = ''
    for letter in phrase:
        if letter == ' ':
            print letter
            word_list.append(word)
            word = ''
        else:
            word = word + letter
    word_list = [i for i in word_list if i]
    for word in word_list:
        if word in words:
            words[word] += 1
        else:
            words[word] = 1
    return words
