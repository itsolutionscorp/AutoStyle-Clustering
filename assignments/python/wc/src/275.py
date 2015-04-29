from collections import Counter
def word_count(mylist):
    counts = {}
    for word in mylist.split():
        counts[word] = mylist.count(word)
    print(counts)
