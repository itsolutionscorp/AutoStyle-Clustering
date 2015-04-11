#import string
from collections import defaultdict

def word_count(input):
    """
    input: Text to parse
    returns frequency of individual words

    but what is a 'word'?
    according to the test cases
    a contiguous set of letters
    a number

    it seems sensible to also include hyphenated words

    the test cases also suggest different case does not
    different words make
    """

    frequencies = defaultdict(int)

    for word in input.split(' '):
        word = ''.join([x for x in word if x.isalpha() or x.isdigit() or x == "-"]).lower()
        if len(word) == 0: #'word' was all non-alphanumeric characters
            continue
        frequencies[word.lower()] += 1
    return dict(frequencies)
