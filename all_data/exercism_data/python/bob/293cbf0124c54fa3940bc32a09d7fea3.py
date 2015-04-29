class Bob(object):
    """Teenager response simulator"""

    @classmethod
    def hey(cls, statement):
        """Return a reponse to statement"""
        statement = statement.strip()

        if cls._is_silence(statement):
            return 'Fine. Be that way!'

        if cls._is_shout(statement):
            return "Woah, chill out!"

        if cls._is_question(statement):
            return "Sure."

        return "Whatever."

    @staticmethod
    def _is_silence(statement):
        return statement == ""

    @staticmethod
    def _is_question(statement):
        return statement.endswith('?')

    @staticmethod
    def _is_shout(statement):
        has_upper = any(c for c in statement if c.isupper())
        has_lower = any(c for c in statement if c.islower())
        return has_upper and not has_lower
