class Bob(object):

    def responses(self):
        return [(is_silence,  "Fine. Be that way!"),
                (is_shout,    "Woah, chill out!"  ),
                (is_question, "Sure."             ),
                (default,     "Whatever."         )]

    def hey(self, msg):
        for (predicate, response) in self.responses():
            if predicate():
                return response
        return "Whatever."

def is_silence(msg):
    return not msg.strip()

def is_shout(msg):
    return msg.isupper()

def is_question(msg):
    return msg.endswith("?")

def default(msg):
    return True
