def word_count(input):
    input = input.split()
    words = []
    count = []
    for item in input:
        index=0
        newWord=True
        for word in words:
            if item == word:
                count[index]+=1
                newWord=False
            index+=1
        if newWord:
            words.append(item)
            count.append(1)
    output = dict(zip(words,count))
    return output
