def word_count(sentence):
    if type(sentence) != type("I don't know the syntax for type(type)"):
        return "You must input a string"
    words=sentence.split()
    x=True
    while x == True:
        try:
            words.remove("")
        except:
            x=False

    dic={}
    
    for word in words:
        try:
            dic[word]=dic[word]+1
        except:
            dic[word]=1
    return dic
            
        
