class Bob(object):
    def hey(self, text):
        text = text.strip()

        response = "Whatever."

        if not text:    # test if text is empty
            response = "Fine. Be that way!"
           
        if text.endswith("?"):
            response = "Sure."

        if text.isupper():
            response = "Woah, chill out!"

        return response
