class Beer:

    def verse(self, number):
        
      current = "{:d} bottles".format(number)
      what = "one";

      if number == 0:
        return ("No more bottles of beer on the wall, no more bottles of beer.\n"
        +"Go to the store and buy some more, 99 bottles of beer on the wall.\n")
     
      bottles = self.get_bottles(number)
      next_bottles = self.get_next_bottles(number)
      what = self.get_what(number)


      words = ("{:s} of beer on the wall, {:s} of beer.\n"
        +"Take {:s} down and pass it around,"
        +" {:s} of beer on the wall.\n").format(bottles, bottles, what, next_bottles)
      return words

    def sing(self, number, stop_at = 0):
      song = ""
      while number >= stop_at:
        song += self.verse(number)
        song += "\n"
        number -= 1
      return song

    def get_bottles(self, number):
    	return "1 bottle" if number == 1 else "{:d} bottles".format(number)

    def get_next_bottles(self, number):
    	if number == 2:
    		return "1 bottle"
    	elif number == 1:
    		return "no more bottles"
    	else:
    		return "{:d} bottles".format(number-1)

    def get_what(self, number):
    	return "it" if number == 1 else "one"
