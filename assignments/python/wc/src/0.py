def word_count(phrase):
    words = dict()
    phrase = phrase.replace('\n', ' ')
    phrase = phrase.replace('\t', ' ')
    while True:
        part = phrase.partition(' ')
        phrase = part[2]
        if part[0] != '':
            if part[0] in words:
                words[part[0]] += 1
            else:
                words[part[0]] = 1
        if phrase.find(' ') == -1:
            break
    if part[2] != '':            
        if part[2] in words:
            words[part[2]] += 1
        else:
            words[part[2]] = 1
    return words
