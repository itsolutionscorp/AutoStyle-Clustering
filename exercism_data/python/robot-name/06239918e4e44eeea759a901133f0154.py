from random import randint
from string import ascii_uppercase

class Robot():

    usedNames = set()

    def __init__( self ):
        self.name = self.generate_name()

    def reset( self ):
        self.name = self.generate_name()

    def generate_name( self ):
        uniqueName = False
        while not uniqueName:
            name = ''
            name += ascii_uppercase[ randint(0,25) ]
            name += ascii_uppercase[ randint(0,25) ]
            name += str( randint(0,9) )
            name += str( randint(0,9) )
            name += str( randint(0,9) )
            uniqueName = name not in self.usedNames
        self.usedNames.add( name )
        return name
