
#words, words, words!
from collections import defaultdict

def word_count(words):
    countDict = defaultdict(lambda:0) ##dictionary for storing output count. defaults to anonymous function which returns 0
    for word in words.split():
        countDict[word]+=1 ##increment dictionary values, new values are initialized at 0, then incremented to 1 when this line is called.
    return countDict
