# Function will take a sentence as the input and return
# the dictionary word_dict with each unique word as the
# key and the number of occurences of that word in the
# sentence as the value.

def word_count(input):

    words = input.split()
    word_dict = {}

    for x in range(len(words)):
        word = words[x]
        if word in word_dict:
            word_dict[word] += 1
        else:
            word_dict[word] = 1

    return word_dict
