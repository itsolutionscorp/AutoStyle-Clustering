class Beer:
	
    def get_verse_components(self, number):
        components = { 2: { "bottles": "2 bottles", "next": "1 bottle", "action": "Take one down and pass it around" },
        			   1: { "bottles": "1 bottle", "next": "no more bottles", "action": "Take it down and pass it around" },
        			   0: { "bottles": "no more bottles", "next": "99 bottles", "action": "Go to the store and buy some more" } }
        defaults = 		  {"bottles": "{:d} bottles".format(number), "next": "{:d} bottles".format(number-1), "action": "Take one down and pass it around" }
        return components.get(number, defaults)
	
    def verse(self, number):
        vc = self.get_verse_components(number)
        return "".join([ vc["bottles"].capitalize(), " of beer on the wall, ",
		         	   	 vc["bottles"], " of beer.\n",
				 		 vc["action"], ", ", vc["next"], " of beer on the wall.\n" ])

    def sing(self, number, stop_at = 0):
        song = []
        for n in range(number, stop_at-1, -1):
            song.append(self.verse(n) + "\n")
        return "".join(song)
