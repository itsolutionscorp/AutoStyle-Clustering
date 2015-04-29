import re

# regex for matching words and numbers
wordre = re.compile(r"[a-zA-Z0-9]+")

# returns the count of each word in a phrase
def word_count(phrase):
    # create a dictionary to hold the counts
    counts = {}

    # find each word in phrase
    for word in wordre.findall(phrase):
        # lowercase it, so that case doesn't matter
        word = word.lower()
        # increase the count for the current word.
        counts[word] = counts.get(word, 0) + 1

    return counts
