# This seems reasonable.  I put all of the nucleotides here
# in case I ever need to come back and add to this code.

class DNA(object):
    PARAMETERS = {
        "A": "A","C": "C","G": "G","T": "T","U": "U",
    }

    # Create an instance of the DNA class and give it a 'sequence' input.

    def __init__(self, sequence):
        self.sequence = sequence

    # Take input sequence and convert it.  This one makes sense to me.
    # Would it also be possible to do this using string.replace instead
    # of sequence.replace?

    def convert(self):
        return self.sequence.replace(self.PARAMETERS["T"], self.PARAMETERS["U"])
