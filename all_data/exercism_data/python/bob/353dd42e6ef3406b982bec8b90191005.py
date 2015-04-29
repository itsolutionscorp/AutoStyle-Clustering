def hey(question):

    response = ''
    
    if question.strip()=='':
        #addressing him without actually saying anything
        response = 'Fine. Be that way!'
    else:
        #check to see if there are any letters in the question
        any_letters = any([i.isalpha() for i in question])
        
        if question.upper()==question and any_letters:
            #if there are letters and you are shouting then chill out
            response = 'Whoa, chill out!'
        elif question[-1]=='?':
            #if it's a question then sure
            response = 'Sure.'
        else:
            #otherwise whatever
            response = 'Whatever.'
            
    return response

    
