class Beer(object): 

    # By storing the verse format in a list like this, 
    # duplication is limited to the data, not the code. 
    # I felt this was a very readible way to implement it 
    # and involved the simplest logic.  Performance-wise, 
    # it uses slightly more memory but should be faster 
    # as string operations are more limited. 

    VERSE_FORMATS = [
        "No more bottles of beer on the wall, no more bottles of beer.\n"
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n",

        "1 bottle of beer on the wall, 1 bottle of beer.\n"
        "Take it down and pass it around, no more bottles of beer on the wall.\n",

        "2 bottles of beer on the wall, 2 bottles of beer.\n"
        "Take one down and pass it around, 1 bottle of beer on the wall.\n",

        "{bottle_count} bottles of beer on the wall, {bottle_count} bottles of beer.\n"
        "Take one down and pass it around, {new_bottle_count} bottles of beer on the wall.\n"
    ]
  

    def __init__(self):
        pass

    def _get_verse(self, count):
        return self.VERSE_FORMATS[ count if count < 3 else 3 ].format(
            bottle_count = count, 
            new_bottle_count = count - 1
        )

    def verse(self, verse_number):
        if not isinstance( verse_number, (int, long) ) or verse_number < 0 or verse_number > 99:
            raise ArgumentError("verse number must be a positive integer between 0 and 99")

        return self._get_verse(verse_number)

    
    def sing(self, start, end=0):
        result = []
        for i in range(start, end - 1, -1):
            result.append(self.verse(i))

        return "\n".join(result) + "\n"
