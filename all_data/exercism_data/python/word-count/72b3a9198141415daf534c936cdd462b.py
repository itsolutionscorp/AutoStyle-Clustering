#count words in phrase


def strip_punct(phrase):

    phrase_clean = ''

    for letter in phrase:

        if letter.isalnum() or letter.isspace():

            phrase_clean += letter

    return phrase_clean

def word_count(phrase):

    count = {}

    words = strip_punct(phrase).lower().split()

    for word in words:

        if word not in count:

            count[word] =1

        else:

            count[word] +=1  

    return count  
