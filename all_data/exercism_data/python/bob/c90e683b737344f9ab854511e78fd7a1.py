'''Bob is a lackadaisical teenager.
In conversation, his responses are very limited.

Bob answers 'Sure.' if you ask him a question.
He answers 'Woah, chill out!' if you yell at him.
He says 'Fine. Be that way!' if you address him
without actually saying anything.
He answers 'Whatever.' to anything else.
'''

def hey(message):
    '''Send bob a message (string)'''
    message = message.strip()
    if message == '':
        return 'Fine. Be that way!'
    elif message.isupper():
        return 'Woah, chill out!'
    elif message.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
