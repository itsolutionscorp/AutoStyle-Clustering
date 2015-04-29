def hey(prompt):
        if prompt.isspace() or prompt == '':
            return 'Fine. Be that way!'
        elif prompt.isupper():
            return 'Whoa, chill out!'
        elif prompt[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'

