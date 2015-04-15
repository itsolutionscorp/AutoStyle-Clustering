# bob.py

class Bob(object):

    def hey(self, user_input=None):

        if not user_input or user_input.strip() == '':
            return "Fine. Be that way!"
        elif user_input.isupper():
            return "Woah, chill out!"
        elif user_input.endswith("?"):
            return 'Sure.'
        else:
            return "Whatever."
