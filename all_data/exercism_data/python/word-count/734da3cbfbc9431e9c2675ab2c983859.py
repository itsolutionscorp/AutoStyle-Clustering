#
# Skeleton file for the Python "Word Count" exercise.
#
def word_count(word):
    words_list = word.split( )
    return {x: words_list.count(x) for x in set(words_list)}
