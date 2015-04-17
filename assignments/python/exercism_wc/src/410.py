"""
    Word Counter
    Write a program that given a phrase can count the occurrences of each word in that phrase.
    For example for the input `"olly olly in come free"`
    olly: 2
    in: 1
    come: 1
    free: 1
"""
def word_count(phrase):
    """Split phrase on spaces. Count occurrence of each word."""
    wordarray = phrase.split()
    wordcount = {}
    for word in wordarray:
        if word not in wordcount:
            wordcount[word] = 1
        else:
            wordcount[word] += 1
    return wordcount
