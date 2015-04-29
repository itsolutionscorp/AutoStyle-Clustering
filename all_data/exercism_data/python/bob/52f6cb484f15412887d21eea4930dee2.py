class Bob():
    def hey(self, input_string):
        if len(input_string) == 0:
            output_string = 'Fine. Be that way.'
        elif input_string[-1] == '.':
            output_string = 'Whatever.'
        elif input_string[-1] == '?':
            output_string = 'Sure.'
        elif input_string.isupper():
            output_string = 'Woah, chill out!'
        else:
            output_string = ''
        return output_string
