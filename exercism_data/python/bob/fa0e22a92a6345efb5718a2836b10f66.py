class Bob:
    "A lackadaisical teenager"
    def hey(self,phrase):
        "Say something to Bob. You might get an useful answer."
        __statement = self.__Statement(phrase)
        if (__statement.isEmpty()):
            return "Fine. Be that way."
        if (__statement.isShooting()):
            return "Woah, chill out!"
        if (__statement.isQuestion()):
            return "Sure."
        return "Whatever."

    class __Statement:
        "Class analysing statements according to Bob"
        def __init__(self,phrase):
            self.__statement = phrase
        def isEmpty(self):
            "Check if the statement is silent"
            return len(self.__statement.strip()) == 0
        def isQuestion(self):
            "Is the statement a question?"
            return self.__statement[-1] == "?"
        def isShooting(self):
            "Check if the statement is written in capital"
            return self.__statement.isupper()
