class Bob(object):

    def hey(self, talk):
        if talk.isupper():
            return "Whoa, chill out!"
        elif talk.endswith("?"):
            return "Sure."
        elif talk.isspace() or talk == '':
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
