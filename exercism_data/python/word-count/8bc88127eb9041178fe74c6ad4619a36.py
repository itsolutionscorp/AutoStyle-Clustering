"""amyfu's solution to exercism word_count"""

def word_count(sentence):
    """This function takes a sentence, and returns the occurrences of each word
    in that phrase."""

    word_list = sentence.split()
    word_dict = {}

    for word in word_list:
        if word in word_dict.keys():
            word_dict[word] += 1
        else:
            word_dict[word] = 1

    return word_dict
