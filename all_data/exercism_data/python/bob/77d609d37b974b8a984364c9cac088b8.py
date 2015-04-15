def hey(input = ''):
    """
    A kind buddy to chat with. Input some string. He'll anwser something smart
    """
    input2 = input.strip()
    
    if input2 == '':
        return 'Fine. Be that way!'
    
    isQuestion = (input2[-1] == '?')
    isCapital = input2.isupper()
    
    if isCapital:
        return 'Whoa, chill out!'
    elif isQuestion:
        return  'Sure.'
    else:
        return 'Whatever.'
        
if __name__ == '__main__':
    pass
