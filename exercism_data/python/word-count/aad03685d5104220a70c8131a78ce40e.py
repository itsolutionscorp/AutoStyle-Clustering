def word_count(text):
    count = {}
    word = ''
    for letter in text:
        if letter == ' ':
            if word not in count:
                count[word] = 1
            else:
                count[word] += 1
            word = ''
        else:
            word += letter
    return count
