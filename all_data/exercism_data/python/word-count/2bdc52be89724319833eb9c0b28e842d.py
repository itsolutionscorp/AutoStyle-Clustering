""" Returns counts of each word in given string s
"""
def word_count(s):
    # Dictionary to hold counts of each word
    counts = {}
    # Skip remove non-alphanumeric characters in string
    # and split the string into a list of words
    words = ''.join(c for c in s if c.isalnum() or c.isspace()).split()
    # Iterate through list of words
    for w in words:
        # Make word lowercase
        temp = w.lower()
        # Add word to dictionary counts
        if temp not in counts:
            counts[temp] = 1
        # Increment count for word
        else:
            counts[temp] += 1
    return counts
