import string

#Takes as input a string of someone's conversation with 'bob'.
def hey(personStatement): 
    #if the person is yelling
    if (personStatement.isupper() ):
        return("Whoa, chill out!")
    #if the person asks a question
    elif (personStatement.endswith('?')):
        return("Sure.")
    #if whitespace only or the empty string is given
    elif (personStatement.isspace() or personStatement == ""): 
        return("Fine. Be that way!")
    else:
        return("Whatever.")
