class BeerSong
	def verse(verse)
		@count = verse
		"#{count(@count).capitalize} #{bottles(@count)} of beer on the wall, #{count(@count)} #{bottles(@count)} of beer.\n" +
    			"#{action}, #{count(@count)} #{bottles(@count)} of beer on the wall.\n"
	end

	def count(verse)
		if (verse == 0)
			return "no more"
		else
			return verse.to_s
		end
	end


	def bottles(verse)
		if (verse == 1)
			return "bottle"
		else
			return "bottles"
		end
	end

	def action
		it = (@count == 1 ? "it" : "one")
		if (@count == 0)
			@count = 99
			return "Go to the store and buy some more"
 		else
 			@count -= 1
 			"Take #{it} down and pass it around"
 		end
	end

	def verses(start, finish = 0)
		(start.downto(finish)).map { |v| "#{verse(v)}\n"}.join
	end

  def sing
    verses(99)
  end
end
