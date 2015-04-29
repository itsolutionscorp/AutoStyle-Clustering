def word_count(sentence):
    buffr = []
    for char in sentence:
        if char.isalnum() or char == ' ':
            buffr.append(char.lower())
    sentence = ''.join(buffr).split()
    count = {}
    for word in sentence:
        if word in count:
            count[word] += 1
        else:
            count[word] = 1
    return count
