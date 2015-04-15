class Bob:
    def hey(self, input):
        if input == '':
            return 'Fine, be that way.'
        elif input == input.upper():
            return 'Woah, chill out!'
        elif input[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
