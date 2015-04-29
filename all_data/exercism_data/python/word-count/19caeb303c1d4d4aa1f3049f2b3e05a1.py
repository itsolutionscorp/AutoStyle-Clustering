import re
from collections import Counter


def word_count(sentence):
    # Concise one-line return that does the following:
    # Convert sentence to lower
    # Delete (re.sub '') all non-lowercase, non-digits, non-spaces
    # Split sentence into words
    # Run Counter to return the count
    return Counter(re.sub('[^a-z\d\s]', '', sentence.lower()).split())
