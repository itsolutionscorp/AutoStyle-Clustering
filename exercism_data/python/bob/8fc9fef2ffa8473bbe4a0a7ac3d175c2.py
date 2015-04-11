class Bob:
    def hey(self,input):
        if input[len(input)-1:] == '?' and not input.isupper():
            return 'Sure.'
        elif not input or input.isspace():
            return 'Fine. Be that way!'
        elif input.isupper():
            return 'Woah, chill out!'
        else:
            return 'Whatever.'
