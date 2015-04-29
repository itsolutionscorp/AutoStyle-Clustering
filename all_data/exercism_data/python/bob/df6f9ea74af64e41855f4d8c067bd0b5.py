__all__ = ["Bob"]

def str_is_upper(str):
    """This method checks whether string `str` is upper case."""
    return str.upper() == str

def str_ends_with(str, end):
    """This method checks whether string `str` ends with `end`."""
    return str[-len(end):] == end

class Bob(object):
    """Bob is a lackadaisical teenager."""
    def hey(self, msg):
        """In conversation, Bob's responses are very limited."""
        if msg == "":
            return "Fine. Be that way."
        elif str_is_upper(msg):
            return "Woah, chill out!"
        elif str_ends_with(msg, "?"):
            return "Sure."
        else:
            return "Whatever."
