#!/usr/local/bin/python3

def hey(string):

    # If you don't say anything to Bob...
    if string.lstrip().rstrip() == '':
        # Here, we're stripping out any whitespace around the string.
        # If the string consists solely of whitespace, we get an empty string.
        return 'Fine. Be that way!'

    # If you ask Bob a queestion...
    if string.endswith('?'):
        return evaluate_question(string)
    
    # If you yell at Bob...
    if string == string.upper():
        return evaluate_yelling(string)

    # If you say anything else to Bob...
    return 'Whatever.'

def evaluate_yelling(string):
    # We're here because the given string matches an all-uppercase version of
    # itself. The problem with that simple test is that numbers will always
    # match, so if we receive an all-numeric string, it will match as yelling.
    # Our job is to now determine whether this string is just numbers.

    # Get a string containing only the alpha characters.
    alpha_string = filter_non_alpha(string)

    # If the string is now empty, it didn't contain any letters. Therefore, we
    # can assume no yelling took place, and we will return our default response.
    if len(alpha_string) == 0:
        return 'Whatever.'

    # Otherwise we'll assume we got yelled at.
    return 'Whoa, chill out!'

def evaluate_question(string):
    # We're here because the given string ended with a question mark.
    # We need to evaluate the string to find out if:
    #  - Bob is being yelled at (all caps)
    #  - Bob is actually being asked a question.

    # We already know the string ends with a question mark, so we can remove it.
    string = string[0:-1]

    # Is the string now strictly numeric? If so, we can assume it to be a
    # question.
    if string.isnumeric():
        return 'Sure.'

    # Is the string the same as an all-uppercase version of itself?
    # If so, it constitues YELLING, so we return the yelling response.
    if string == string.upper():
        return 'Whoa, chill out!'

    # If we haven't returned any other response, we can safely assume this was
    # a question, so we return the appropriate response.
    return 'Sure.'

def filter_non_alpha(string):
    # Filter out non-alpha characters from string.
    
    # Use a list comprehension to get only the alpha characters from the string.
    # This results in a list.
    alpha_list = [char for char in string if char.isalpha()]
    
    # Use join() to join the elements of the list into a string. Here, we're 
    # joining with an empty string between the elements.
    alpha_string = ''.join(alpha_list)
    
    # Finally, return the alpha-only string.
    return alpha_string
