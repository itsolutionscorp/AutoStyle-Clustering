import re

class Bob:
    def hey( self, address ):
        if re.match( '^\s*$', address ):
            return "Fine. Be that way!"

        # account for terribly excited spelling
        address = re.sub( '![!1]+$', '!', address )

        if re.search('[A-Z]', address ) and address == address.upper():
            return "Woah, chill out!"

        if address[-1] == '?':
            return "Sure."

        return "Whatever."
