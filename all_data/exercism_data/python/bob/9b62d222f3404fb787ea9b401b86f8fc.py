#!/usr/bin/python
# Joe Karbowski
# Last Modified: 9/26/2014
""" This module returns a string as a response to a string statement.
    The response will vary depending on the type of a statement made to the
    program. The responses are based on the test program and the problem
    statement.
"""
import sys

def hey(statement):
    """ Returns bob's response to statement. Answers are according to the
        prompt contained in the problem description. The answer will vary 
        depending if you yell at bob or ask him a statement
        >>>hey('whats up?')
        'Sure.'
        >>>hey('hey!')
        'Whoa, chill out!'
        >>>hey('you smell funny')
        'Whatever.' """
    return process_question(statement)

def process_question(statement):
    """ returns the type of string that statement is. The possible returns are
        based off of the "statement_type """
    if is_question(statement):
        return "Sure."
    elif is_yell(statement):
        return "Whoa, chill out!"
    elif is_nothing(statement):
        return "Fine. Be that way!"
    else:
        return "Whatever."

def is_ascii(test_str):
    """ Returns true test_str is made of all ascii characters. The function
        return false otherwise.  """
    char_val_list = []
    for i in test_str:
        char_val_list.append(ord(i))
    if max(char_val_list) < 128:
        return True
    else:
        return False

def is_yell(test_str):
    """ Returns true if test_str is a "yell". A yell: Ends with an "!", or Is
        all Upper-case """
    if test_str.isupper():
        return True
    return False

def is_nothing(test_str):
    """ Returns true if test_str is a "nothing." Nothing is made up of all
        white space and/or tabs.  """
    if len(test_str) < 1:
        return True
    elif test_str.expandtabs().isspace():
        return True
    return False

def is_question(test_str):
    """ Returns true if test_str is a "question." Questions are statements that
        end with a "?", and are not all caps, which would be considered a yell.
        """
    if (test_str.endswith('?') and 
        is_ascii(test_str) and 
        not test_str.isupper()):
        return True
    return False

if __name__ == "__main__":
    if(len(sys.argv)>=2):
        _ = sys.argv.pop(0)
        for argument in sys.argv:
            print(hey(argument))
    else:
        print("Not Enough Arguments")
