class Bob():
    def hey(self, sentence):
        # If nothing is said
        if (sentence == None or sentence.strip() == ''):
            return 'Fine. Be that way!'

        # If someone yells (caps)
        elif str.isupper(sentence):
            return 'Woah, chill out!'

        # If a question is asked
        elif sentence.endswith("?"):
            return 'Sure.'

        # Everything else
        else:
            return 'Whatever.'
