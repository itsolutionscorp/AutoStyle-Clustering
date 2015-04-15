class Bob:
    def hey(self, said):
        # Nothing is said
        if said == None or len(said.strip()) == 0:
            return 'Fine. Be that way!'

        # Doesn't like to be yelled at
        # Yelling is indicated by ALL-CAPS
        if said.isupper():
            return 'Woah, chill out!'

        # If asked a question
        if said[-1] == '?':
            return 'Sure.'

        # Default response otherwise
        return 'Whatever.'
