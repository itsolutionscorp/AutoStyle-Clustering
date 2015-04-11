def hey(conversation):
    if conversation.isupper():
        return 'Whoa, chill out!'
    elif conversation.endswith('?'):
        return 'Sure.'
    elif conversation.isspace() or conversation == '':
        return 'Fine. Be that way!'    
    else:
        return 'Whatever.'
