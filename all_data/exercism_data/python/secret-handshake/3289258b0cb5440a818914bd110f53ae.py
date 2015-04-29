import re
import string

def handshake(number):
    # number could be a string or an int
    # if it's an int it will be converted into string
    HANDSHAKE = ['jump', 'close your eyes', 'double blink', 'wink']
    
    # check if binary string is invalid
    if type(number) == str and re.compile('[^01]').search(number):
        number = '0'

    #check if integer is invalid
    elif type(number) == int:
        if number < 0 or number > 31:
            number = '0'
        else:
            number = str(bin(int(number)))[2:]

    # check if reverse bit set
    if len(number) == 5 and number[0] == '1':
        # strip reverse bit
        number = number[1:]
        # reverse number and HANDSHAKE
        number = number[::-1]
        HANDSHAKE.reverse()
     
    # 0 pad binary string
    number = string.zfill(number, 4)
    # map binary string to secret handshake
    return [HANDSHAKE[i] for i in reversed(range(len(number))) if number[i] == '1']
    
        

def code(events):
    HANDSHAKE = ['jump', 'close your eyes', 'double blink', 'wink']
    result = ''
    
    # detect if order of events is reveresed
    if len(events) >= 2 and (events[0] in HANDSHAKE and events[1] in HANDSHAKE) and HANDSHAKE.index(events[0]) < HANDSHAKE.index(events[1]):
        result += '1'
    
    # map the events to a binary string
    for i in range(len(HANDSHAKE)):
        if HANDSHAKE[i] in events:
            result += '1'
        else:
            result += '0'
    result = result.lstrip('0')
    
    
    # if there are unkown events it's invalid
    for event in events:
        if event not in HANDSHAKE:
            result = '0'
    
    return result
