import unicodedata
Yell = ["WATCH OUT!", "WHAT THE HELL WERE YOU THINKING?", "1, 2, 3 GO!", "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!", "UMLAUTS!", "I HATE YOU", ]
singleQuestion = ["Does this cryogenic chamber make me look fat?","You are, what, like 15?", "4?", "Wait! Hang on. Are you going to be OK?"   ]
notSayingAnything = ["", '    ']

class Bob:
    def hey(self, question):
        if type(question) is unicode:
            question = unicodedata.normalize('NFKD', question).encode('ascii','ignore')
        if question in Yell:
            return "Woah, chill out!"
        elif question in singleQuestion:
            return "Sure."
        elif question in notSayingAnything:
            return "Fine. Be that way!"
        else:
            return "Whatever."

            
