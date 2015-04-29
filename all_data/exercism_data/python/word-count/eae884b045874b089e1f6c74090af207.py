def word_count(string):
    strCount = {}

    #remove new lines
    #split into individual words on the whitespaces
    string = string.replace('\n', ' ').split(' ')
    for i in string:
        #count words only if not whitespace
        if i:
            #add word to dict if doesn't exist
            if not i in strCount:
                strCount[i] = 1
            #add one to the counter of that word
            else:
                strCount[i] += 1
    return strCount


if __name__ == '__main__':
    print(word_count('wolf   terry'))
