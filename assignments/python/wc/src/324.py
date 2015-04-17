import re
def word_count(phrase):
    """Counts the occurrences of each word in that phrase."""
    count = 0
    word_count_results = {}
    words = phrase.split()
    for word1 in words:
        if word1 in word_count_results:
            continue
        else:
            for word2 in words:
                if word1 == word2:
                    count +=1
                word_count_results[word1] = count
        count = 0
    return word_count_results
