class Bob:

    def __init__(self):
        self.__responses = []
        self.add_response(lambda msg: not msg or msg.isspace(), "Fine. Be that way.")        
        self.add_response(str.isupper, "Woah, chill out!")  
        self.add_response(lambda msg: msg.endswith("?"), "Sure.")                                       

    def hey(self, message):
        """
        Tell bob a message and return the answer. Uses the responses defined
        with :py:func:`add_response`. If no response matches, returns "Whatever".
        """
        for predicate, response in self.__responses:
            try:
                if predicate(message):
                    return response
            except:
                pass
        return "Whatever."
                

    def add_response(self, predicate, response):
        """Add a response for messages where `predicate(message)` is True."""
        self.__responses.append((predicate, response))
