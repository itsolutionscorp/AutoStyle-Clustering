import string
def check_nothing(s):
    return s.strip() == ''

def check_question(s):
    return s[-1] == '?'
 
def check_yelling(s):
    has_letters = False
    for char in s:
        if char in string.ascii_letters:
            has_letters = True;
            break;

    return has_letters and s.upper() == s
    

def hey(statement):
    Bob = [(check_nothing, 'Fine. Be that way!'),
           (check_yelling, 'Whoa, chill out!'),
           (check_question, 'Sure.')]
    
    for pred, response in Bob:
        if pred(statement):
            return response

    return 'Whatever.'
