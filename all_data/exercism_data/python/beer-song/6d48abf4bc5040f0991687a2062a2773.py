class Beer:
    """ Beer Song Simulator 20XX """

    def sing(self, start_num, end_num=0):
        """ Sing the verses of the beer song from range start to end. """
        assert(start_num >= 0) # I guess negative beer involves upchucking
        current_verse = self.verse(start_num) + "\n"
        if start_num > 0 and start_num > end_num:
            # Fetch the other verses of the song and return the appended results
            return current_verse + self.sing(start_num-1, end_num)
        else:
            # This is the final verse being sung
            return current_verse

    def verse(self, num_bottles):
        """ Recite the n-th verse of the beer song. """
        if num_bottles == 0:
            return ("No more bottles of beer on the wall, no more bottles of beer.\n"
                    "Go to the store and buy some more, 99 bottles of beer on the wall.\n")
        elif num_bottles == 2:
            return ("2 bottles of beer on the wall, 2 bottles of beer.\n"
                    "Take one down and pass it around, 1 bottle of beer on the wall.\n")
        elif num_bottles == 1:
            return ("1 bottle of beer on the wall, 1 bottle of beer.\n"
                    "Take it down and pass it around, no more bottles of beer on the wall.\n")
        else:
            return ("%d bottles of beer on the wall, %d bottles of beer.\n"
                    "Take one down and pass it around, %d bottles of beer on the wall.\n"
                   ) % (num_bottles, num_bottles, num_bottles-1)
