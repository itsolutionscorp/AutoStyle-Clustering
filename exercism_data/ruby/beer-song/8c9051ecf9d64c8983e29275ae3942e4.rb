class Beer
	def verse(bottles)
		case bottles
		when 3..99 then "#{bottles} bottles of beer on the wall, #{bottles} bottles of beer.\nTake one down and pass it around, #{bottles -1} bottles of beer on the wall.\n"
		when 2 then "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
		when 1 then "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
		when 0 then "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
		end
	end

	def sing(start, finish = 0)
		start.downto(finish).each_with_object("") do |bottles, beer_song|
			beer_song += (verse(bottles) + "\n")
		end
	end
end
