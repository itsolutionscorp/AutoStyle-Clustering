import string
from enum import Enum

Statement = Enum("Statement","question yell empty whatever" )

#Takes as input a string of someone's conversation with 'bob'.
#This method calls checkType() to determine which of the 4 responses to deliver
def hey(personStatement): 
    result = checkType(personStatement)
    if (result == Statement.question):
        return("Sure.")
    elif (result == Statement.yell):
        return("Whoa, chill out!")
    elif (result == Statement.empty): 
        return("Fine. Be that way!")
    else:
        return("Whatever.")

#Takes as input someone's statement to 'bob'.
def checkType(statement):
    statementType = None
    letterCount = 0
    whitespaceCount = 0
    upperStatement = statement.upper()
    
    #loop through all of the characters to count ascii letters and whitespace
    for character in statement:
        if(character in string.ascii_letters):
            letterCount += 1
        if(character in string.whitespace):
            whitespaceCount += 1

    #conditional branch to determine bob's response
    if( statement == upperStatement and letterCount > 0):
        statementType = Statement.yell
        return(statementType)
   
    elif( len(statement) > 0 and statement[-1] == "?"):
        statementType = Statement.question
        return( statementType)

    elif( (len(statement) == 0) or (whitespaceCount >= len(statement))  ):
        statementType = Statement.empty
        return(statementType)

    else:
        statementType = Statement.whatever
        return(statementType)
