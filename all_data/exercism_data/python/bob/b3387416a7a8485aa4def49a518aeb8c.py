""" exercism - python/bob """

class Bob(object):
    """Bob is a lackadasical teenager"""

    def hey(self, msg):
        """used to say something to Bob"""

        if self.silent(msg):
            return 'Fine. Be that way!'
        if self.shouty(msg):
            return 'Woah, chill out!'
        if self.asking(msg):
            return 'Sure.'
        return 'Whatever.'

    @staticmethod
    def silent(msg):
        """true if msg is silence, false otherwise"""
        return msg == None or msg.strip() == ''

    @staticmethod
    def shouty(msg):
        """true if msg is shouting, false otherwise"""
        return msg.isupper and msg == msg.upper()

    @staticmethod
    def asking(msg):
        """true if msg is interrogation, false otherwise"""
        return msg.endswith('?')
