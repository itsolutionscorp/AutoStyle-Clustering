#
#
#

def word_count(phrase):

    words = {}
    s = ''
    letterCount = 0
    phrase = phrase.replace('\n', ' ')
    phrase = ' '.join(phrase.split())

    
    for char in phrase:
        letterCount += 1
        if (char == ' ' or letterCount == len(phrase)):
            count = 0
            if (letterCount == len(phrase)):
                s += char
            for w in words:
                if (s == w):
                    count += 1
            if (count == 0):
                words[s] = 1
            else:
                words[s] += 1
            s = ''
        else:
            s += char

    return words


#word_count('rah rah ah ah ah\nroma roma ma\nga ga oh la la\nwant your bad romance')
