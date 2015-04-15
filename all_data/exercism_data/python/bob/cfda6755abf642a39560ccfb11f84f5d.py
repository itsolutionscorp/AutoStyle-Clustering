# Initialize a class 'Bob'
class Bob:
    # Bob hears you when you say 'hey'!
    def hey(self,text):
        # If you say nothing to bob...
        if not len(text):
            return 'Fine. Be that way.'
        # If you ask bob a question...
            # If text is BOTH a question AND shouted, response is based
            # on question
        if text[-1] == "?":
            return "Sure."
        # If you shout at bob...
        if text.isupper():
            return "Woah, chill out!"
        # If you have done none of the above
        return 'Whatever.'
