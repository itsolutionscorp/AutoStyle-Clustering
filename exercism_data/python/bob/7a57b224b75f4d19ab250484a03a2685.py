'''
Bob is a lackadaisical teenager. In conversation, his responses are very limited.
Bob answers <Sure.> if you ask him a question.
He answers <Woah, chill out!> if you yell at him (ALL CAPS).
He says <Fine. Be that way!> if you address him without actually saying anything.
He answers <Whatever.> to anything else.
'''
import re
reSpace = re.compile("^\s*$")

class Bob:
    @classmethod
    def isFine(cls, message):
        return message is None or reSpace.match(message)

    @classmethod
    def isWoah(cls, message):
        uMessage = message.decode('latin9').encode('utf8')
        return uMessage.decode('utf8').isupper()

    @classmethod
    def isSure(cls, message):
        return len(message) > 1 and message[-1]=="?"

    def hey(self, message):
        for currentTest, answers in Bob.theTests:
            if currentTest(message):
                return answers
        return('Whatever.')
    
Bob.theTests = [[Bob.isFine, 'Fine. Be that way!'],
                [Bob.isWoah, 'Woah, chill out!'],
                [Bob.isSure, 'Sure.']]
