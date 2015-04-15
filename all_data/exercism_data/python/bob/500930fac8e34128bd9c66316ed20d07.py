#Bob is a lackadaisical teenager.
#In conversation, his responses are very limited.

#Bob answers 'Sure.' if you ask him a question.

#He answers 'Whoa, chill out!' if you yell at him.

#He says 'Fine. Be that way!' if you address him without actually saying
#anything.

#He answers 'Whatever.' to anything else.

def hey(msg):
    msg = msg.strip()

    #Checks if the message is empty
    if (len(msg) == 0):
        return "Fine. Be that way!"

    #Checks is the message is yelling
    if (msg.isupper()):
        return "Whoa, chill out!"

    #Checks if the message is a question
    if (msg[-1] == "?"):
        return "Sure."
 
    return "Whatever."
