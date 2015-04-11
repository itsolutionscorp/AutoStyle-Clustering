
import string

class Bob(object):

    def isQuestion(self, sentence):
        return sentence.endswith('?')

    # def isAllCaps(self, sentence):
    #     isUppercase = False
    #     for letter in sentence:
    #         if letter.islower():
    #             return False
    #         if letter.isupper():
    #             isUppercase = True
    #     return isUppercase

    def isAllCaps(self, sentence):
        if any(letter for letter in sentence if letter.islower()):
            return False
        elif any(letter for letter in sentence if letter.isupper()):
            return True
        return False

    def isBlank(self, sentence):
        return sentence.strip() == ''

    def hey(self, sentence):
        if self.isAllCaps(sentence):
            return "Woah, chill out!"
        elif self.isQuestion(sentence):
            return "Sure."
        elif self.isBlank(sentence):
            return "Fine. Be that way!"
        else:
            return "Whatever."

b = Bob()
