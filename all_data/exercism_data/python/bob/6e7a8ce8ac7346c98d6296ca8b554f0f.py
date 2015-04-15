class Bob:
    "Bob, the teenager. See README for instructions."
    
    def hey(self, message):
        """Speak to bob.
        
        Parameters
        ----------
        message : string
           A message to Bob

        Returns
        -------
        string
           Bob's reply, as specified in the README.
        """
        message = message.strip()
        if message.isupper():
            return 'Woah, chill out!'
        elif message.endswith('?'):
            return 'Sure.'
        elif not message:
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
