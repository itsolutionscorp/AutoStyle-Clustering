#
# A program that given a phrase can count the occurrences of each word in that phrase.
#
# For example for the input `"olly olly in come free"`
#
# ```plain
# olly: 2
# in: 1
# come: 1
# free: 1
# ```


def word_count(sentence):

    if not sentence:
        return ''

    words = sentence.split()
    word_counts = {}

    for word in words:
        if word not in word_counts:
            word_counts[word] = 1
        else:
            word_counts[word] += 1

    return word_counts
