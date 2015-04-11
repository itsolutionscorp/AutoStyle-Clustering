def encode(words):
    if not words:
        return ''
    ls = [i for i in words.lower() if i.isalnum()]
    
    for i in range(len(words)):
        if i**2>=len(ls):
            length = i
            break
    myString = ''
    for j in range(length):
        for i in range((len(ls)//length+1)):
            try:
                myString += ls[i*length:(i+1)*length][j]
            except IndexError:
                break
        myString += ' '
    return myString.strip()
        
    
