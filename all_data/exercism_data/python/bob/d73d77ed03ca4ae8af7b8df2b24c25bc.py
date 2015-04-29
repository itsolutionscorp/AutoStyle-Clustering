# Bob's hey function

def is_yell(str1):
    return str1.isupper()

def is_question(str1):
    return (str1[-1] == '?')

def is_whitespace_or_empty(str1):
    return (str1.isspace() or len(str1) == 0)

def hey(input_str):        
    if is_yell(input_str):
        return 'Woah, chill out!'
    elif is_whitespace_or_empty(input_str):
        return 'Fine. Be that way!'
    elif is_question(input_str):
        return 'Sure.'
    else:
        return 'Whatever.'
