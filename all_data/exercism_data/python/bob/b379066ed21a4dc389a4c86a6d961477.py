class Bob:

    def hey(self, comment):

        # Check if comment only contains whitespaces
        if len(comment) == comment.count(' '):
            return 'Fine. Be that way!'

        # Check if comment is all upper case
        elif comment.isupper():
            return 'Woah, chill out!'

        # Check if comment ends on a question mark
        elif comment.endswith('?'):
            return 'Sure.'

        # Default answer
        return "Whatever."
