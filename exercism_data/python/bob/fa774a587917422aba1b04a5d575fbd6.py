def hey(sentence):
    #Yell check (all caps)
    if(sentence.isupper()):
        return "Whoa, chill out!"
    #Question check (ends in question mark?)
    elif(sentence.endswith('?')):
        return "Sure."
    #Null check (Is nothing said?)
    elif(sentence.strip() == ''):
        return "Fine. Be that way!"
    else:
        return "Whatever."
      
