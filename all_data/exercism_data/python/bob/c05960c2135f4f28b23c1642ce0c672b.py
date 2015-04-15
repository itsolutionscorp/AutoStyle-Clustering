class Bob(object):

    answers = [ 'Woah, chill out!', 
                'Fine. Be that way!',
                'Sure.', 
                'Whatever.'         ]

    def hey( self, text ):

        if text.isupper():
            return Bob.answers[ 0 ]

        if self.isEmpty( text ):
            return Bob.answers[ 1 ]
        
        if self.isQuestion( text ):
            return Bob.answers[ 2 ]

        return Bob.answers[ 3 ]


    def isEmpty( self, text ):

        return text == '' or text.isspace()


    def isQuestion( self, text ):

        return text[-1] == '?'
