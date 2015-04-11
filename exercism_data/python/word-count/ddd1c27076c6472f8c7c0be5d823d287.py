# Word Count
#    Write a program that given a phrase can count the occurrences of each word in that phrase.
#    For example for the input `"olly olly in come free"`
#    
def word_count(line):
    table = dict()
    tokens = line.split();
    for word in tokens:
        word = word.strip()
        if table.has_key(word):
            count = table.get(word)
            count = count + 1
            table[word] = count
        else:
            table[word] = 1
            
    return table
