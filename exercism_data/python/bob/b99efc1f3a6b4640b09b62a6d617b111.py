class Bob:
    """A teenager."""
    
    def hey(self, statement):
        """Communicate with Bob."""
        statement = statement.strip()
        if not statement:
            return "Fine. Be that way!"
        if statement.isupper():
            return "Woah, chill out!"
        elif statement.endswith('?'):
            return "Sure."
        elif statement:
            return "Whatever."
