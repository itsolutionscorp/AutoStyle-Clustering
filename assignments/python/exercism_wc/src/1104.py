def word_count(str):
    """Word Count
    Count the occurences of each word in the string"""
    count = {}
    for cur in str.split():
        count[cur] = count.get(cur, 0) + 1
    return count
