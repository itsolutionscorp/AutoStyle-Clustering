# Word Count
#    Write a program that given a phrase can count the occurrences of each word in that phrase.
#    For example for the input `"olly olly in come free"`
#    
from collections import Counter

def word_count(line):
    counter = Counter()
    for word in line.split():
        counter[word] += 1            
    return counter
