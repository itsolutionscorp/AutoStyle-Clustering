class Bob():

    def hey(self, input):
        response = 'Whatever.'

        if not input or not input.strip():
            response = 'Fine. Be that way!'

        elif input == input.upper():
            response = 'Woah, chill out!'

        elif input[-1] == '?':
            response = 'Sure.'

        return response
