'''
Write a program that given a phrase can count the occurrences of each word in that phrase.
For example for the input `"olly olly in come free"`
```plain
olly: 2
in: 1
come: 1
free: 1
```
'''
def word_count(phrase):
    count = {}   #Defining an empty dictionary
    for word in phrase.split():
        if word in count:
             count[word] += 1   #Increment value by 1 for the key named $word
        else:
             count[word] = 1   #Add a key named $word with a value of 1
    return count   #return the dictionary
