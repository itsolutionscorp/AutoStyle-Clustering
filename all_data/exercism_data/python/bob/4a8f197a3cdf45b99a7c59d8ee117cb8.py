class Bob():
    def hey(self, sentence):
        # If nothing is said
        if (sentence is None or sentence.strip() == ''):
            return 'Fine. Be that way!'

        # If someone yells (caps)
        if sentence.isupper():
            return 'Woah, chill out!'

        # If a question is asked
        if sentence.endswith("?"):
            return 'Sure.'

        # Everything else
        return 'Whatever.'
