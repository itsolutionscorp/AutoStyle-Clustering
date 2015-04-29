class Bob:

    def __init__(self):
        self.name = "Bob"

    def hey(self, message):
        if not message:
            response = "Fine. Be that way!"

        else:
            allcaps = True
            allspaces = True
            for ch in message:
                if ch.islower():
                    allcaps = False
                    allspaces = False
                elif ch != " ":
                    allspaces = False

            if allspaces:
                response = "Fine. Be that way!"
            elif allcaps:
                response = "Woah, chill out!"
            elif message[-1] == "?":
                response = "Sure."
            else:
                response = "Whatever."
        
        return response
