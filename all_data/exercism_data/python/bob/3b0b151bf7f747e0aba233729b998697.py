def hey(statement):
    """Returns Bob the lackadaisical teenager conversation responses"""

    #He says 'Fine. Be that way!' if you address him without actually saying anything.
    if statement.strip() == '':
        return "Fine. Be that way!"
    #Bob answers 'Sure.' if you ask him a question.
    elif statement[len(statement)-1] == "?" and\
         statement.isupper() == False:
         return "Sure."
    #He answers 'Whoa, chill out!' if you yell at him.
    elif (statement.isupper()) or \
         ((statement.isupper()) and\
          (statement[len(statement)-1] == "!" or \
           statement[len(statement)-1] == "?")
          ):
        return "Whoa, chill out!"
    #He answers 'Whatever.' to anything else.
    else:
        return "Whatever."
