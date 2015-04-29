class Bob(object):
    # From the README: "Bob is a lackadaisical teenager. In conversation, his
    # responses are very limited."
    def hey(self, input=''):
        if input is None or not input.strip():
            # He says 'Fine. Be that way!' if you address him without actually
            # saying anything.
            return 'Fine. Be that way!'
        elif input.upper() == input:
            # He says 'Fine. Be that way!' if you address him without actually
            # saying anything.
            return 'Woah, chill out!'
        elif input.endswith('?'):
            # Bob answers 'Sure.' if you ask him a question.
            return 'Sure.'
        else:
            # He answers 'Whatever.' to anything else.
            return 'Whatever.'
