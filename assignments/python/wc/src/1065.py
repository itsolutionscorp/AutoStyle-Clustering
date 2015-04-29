def word_count(sentence):
    words = {}
    for word in sentence.split():
        if word not in words:
            words['{}'.format(word)] = 1
        else:
            words[word] += 1
    return words
if __name__ == '__main__':
    print(word_count("What the hell is this what the bloody hell"))
