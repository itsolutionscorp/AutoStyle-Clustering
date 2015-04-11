import collections

def word_count(str):
    count = collections.defaultdict(int)
    for word in str.split():
        count[word]+=1
    return count
