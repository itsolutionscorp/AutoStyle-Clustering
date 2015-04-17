def word_count(sent):
    sent += ' '
    worddict = {}
    keyword = ''
    for letters in sent:
        if letters is not ' ':
            keyword += letters
        else:
            if not keyword in worddict:
               worddict[keyword] = 1
               keyword = ''
            else:
               worddict[keyword] += 1
               keyword = ''
    return worddict
