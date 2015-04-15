class string(unicode):
    def __new__(cls, msg):
        return super(string, cls).__new__(cls, msg)

    def __get_attribute__(self, name):
        att = super(string, self).__getattribute__(name)

        if not callable(att):
            return att

        def call_later(*args, **kwargs):
            result = att(*args, **kwargs)
            if isinstance(result, basestring):
                return string(result)
            return result
        return call_later

    def isquestion(self):
        return self.endswith("?")

def hey(msg):
    msg = string(msg)
    if msg.isupper():
        return 'Whoa, chill out!'

    if msg.isquestion():
        return "Sure."

    if msg.isspace() or len(msg)==0:
        return 'Fine. Be that way!' 

    return 'Whatever.'
