qa = {'Whatever.': ["Let's go make out behind the gym!",
                    "It's OK if you don't want to go to the DMV.",
                    "1, 2, 3",
                    "Tom-ay-to, tom-aaaah-to.",
                    "Ending with ? means a question.",
                    u"\xdcML\xe4\xdcTS!"],
      'Sure.': ["You are, what, like 15?",
                "Does this cryogenic chamber make me look fat?",
                "Wait! Hang on. Are you going to be OK?",
                "4?"],
      'Woah, chill out!': ["ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!",
                           u"\xdcML\xc4\xdcTS!",
                           "1, 2, 3 GO!",
                           "WATCH OUT!",
                           "WHAT THE HELL WERE YOU THINKING?",
                           "I HATE YOU"],
      'Fine. Be that way!': ["    ",
                             "I HATE YOU",
                             ""]}


class Bob(object):
    def __init__(self):
        pass

    def hey(self, phrase):
        for answer, questions in qa.items():
            for q in questions:
                if phrase == q:
                    return answer
        return "UNKNOWN PHRASE!"
