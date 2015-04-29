class Bob:
    def hey(self, remark):

        def isSilence(msg):
            return not msg or msg.isspace()

        def isQuestion(msg):
            return msg.endswith('?')

        def isYelling(msg):
            alpha = False
            for m in msg:
                if m.isalpha():
                    alpha = True
            return msg.isupper() and alpha

        if isSilence(remark):
            return "Fine. Be that way!"
        elif isYelling(remark):
            return "Woah, chill out!"
        elif isQuestion(remark):
            return "Sure."
        else:
            return "Whatever."
