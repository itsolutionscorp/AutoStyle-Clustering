#
#
def hey(conversation):
    if conversation.strip() == '':
        return 'Fine. Be that way!'
    elif conversation.isupper():
        return 'Whoa, chill out!'
    elif conversation.endswith('?'):
        return 'Sure.'  
    else:  return 'Whatever.'
