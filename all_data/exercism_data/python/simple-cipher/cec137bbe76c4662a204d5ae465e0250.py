import string
import random

class Caesar():

    def __init__( self ):
        self.cipher = Cipher('d')

    def encode( self, s ):
        return self.cipher.encode( s )

    def decode( self, s ):
        return self.cipher.decode( s )


class Cipher():

    def __init__( self, *args ):
        if len(args) > 0:
            self.key = args[0]
            self.check_key()
        else:
            self.key = ''
            for ii in range(0,100):
                self.key += string.ascii_lowercase[ random.randint(0,25) ]

    def check_key( self ):
        if not self.key.islower():
            raise ValueError( "Key must only contain lowercase letters." )
        for s in self.key:
            if s not in string.ascii_lowercase:
                raise ValueError( "Key must only contain lowercase letters." )

    def encode( self, s ):
        s = self.clean_input( s )
        lc = string.ascii_lowercase
        t = ''
        for ii in range( 0, len(s) ):
            t += lc[ (lc.index( s[ii] ) + lc.index( self.key[ ii % len(self.key) ] )) % 26 ]
        return t

    def decode( self, s ):
        s = self.clean_input( s )
        lc = string.ascii_lowercase
        t = ''
        for ii in range( 0, len(s) ):
            t += lc[ (lc.index( s[ii] ) - lc.index( self.key[ ii % len(self.key) ] )) % 26 ]
        return t

    def clean_input( self, s ):
        lc = string.ascii_lowercase
        s = s.lower()
        t = ''
        for l in s:
            if l in lc:
                t += l
        return t

