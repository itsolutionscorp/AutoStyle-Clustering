class Bob(object):

    def responses(self):
        return [(is_silence,  "Fine. Be that way!"),
                (is_shout,    "Woah, chill out!"  ),
                (is_question, "Sure."             )]

    def hey(self, msg):
        msg = "" if msg is None else msg.strip()
        for (predicate, response) in self.responses():
            if predicate(msg):
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
