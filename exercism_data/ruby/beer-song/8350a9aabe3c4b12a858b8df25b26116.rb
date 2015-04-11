class Beer
	def verse(beers)
		plural_bottles = "bottles"
		if beers == 2
			plural_bottles = "bottle"
		end
		if beers == 1
			"1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
		elsif beers == 0
			"No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
		else
			"#{beers} bottles of beer on the wall, #{beers} bottles of beer.\nTake one down and pass it around, #{beers - 1} #{plural_bottles} of beer on the wall.\n"
		end
	end

	def sing(beer_start, beer_end = 0)
		@song = ""
		total_beers = beer_start
		(beer_start+1 - beer_end).times do
			@song += verse(total_beers) + "\n"
			total_beers -= 1
		end
		@song
	end
end
