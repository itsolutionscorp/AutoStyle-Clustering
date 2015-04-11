import re

class Octal:
    def __init__(self,o):

        result = re.search('([^0-7])', o)
        if result != None:
            raise( ValueError( "Invalid octal digit: %s" % result.group(1) ) )

        self.n = o

    def to_decimal(self):
        return reduce( 
            lambda a, b: 8 * a + b,
            [ int(i) for i in self.n ]
        )
