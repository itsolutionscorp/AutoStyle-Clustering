def hey(inputStr):
    inputStr = inputStr.strip()

    if not inputStr:
        return 'Fine. Be that way!'

    if (_is_yelling(inputStr)):
        return 'Whoa, chill out!'
    
    if (_is_question(inputStr)):
        return 'Sure.'

    return 'Whatever.'    

def _is_question(inputStr):
    return inputStr[-1] == '?'

def _is_yelling(inputStr):
    return inputStr.isupper()
