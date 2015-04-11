def hey(input_string):
    
    # if input empty or spaces 
    if input_string.strip() == "":
        return "Fine. Be that way!" 
    
    # Get upper and lowercase letters
    lowerCaseLetters = [i for i in input_string if (i.isalpha() and i.islower())]
    upperCaseLetters = [i for i in input_string if (i.isalpha() and i.isupper())]
    
    # Determine if all the letters are Capitals / and ensure that there are at least one letter
    upperCase = len(lowerCaseLetters) == 0 and len(upperCaseLetters) > 0
    
    # Determine if this is  a question
    question = input_string[len(input_string) - 1] == '?'

    # if this is a question not in all caps
    if question and not upperCase:
        return "Sure."
    
    # if this question/statement is in all caps
    elif upperCase == True:
        return "Woah, chill out!"

    # default repsonse
    else:
        return "Whatever."
