# Bob

#Bob is a lackadaisical teenager. In conversation, his responses are very limited.

#Bob answers 'Sure.' if you ask him a question.

#He answers 'Whoa, chill out!' if you yell at him.

#He says 'Fine. Be that way!' if you address him without actually saying
#anything.

#He answers 'Whatever.' to anything else.
def hey(says):
    if says.strip()=='':                    #removes all the whitespace from says 
                                            #if there's nothing left then says is empty
                                            #(i.e all whitespace)
        return 'Fine. Be that way!'
    elif says.isupper():                    #if says is all upper case
        return 'Whoa, chill out!'
    elif says.endswith('?'):                #if says ends with '?'
        return 'Sure.'  
    else:   
        return 'Whatever.'
