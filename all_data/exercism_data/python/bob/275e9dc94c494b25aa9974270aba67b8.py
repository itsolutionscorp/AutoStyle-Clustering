import re;

class Bob():
    def hey(self,conversationStarter):

        #if string is a question
        if('?' in conversationStarter):
            return "Sure.";

        #if some one is shouting (allcaps)
        elif (re.compile("[A-Z]+$").match(conversationStarter)):
            return "Woah, chill out!";

        #if string is empty
        elif (not conversationStarter):
            return "Fine. Be that way.";

        else:
            return "Whatever.";
