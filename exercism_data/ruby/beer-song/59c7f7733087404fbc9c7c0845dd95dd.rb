class BeerSong

	def verse(n)
		if n == 2
			return "#{n} bottles of beer on the wall, #{n} bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
		elsif n == 1
			return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
		elsif n == 0
			return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
		else
			return "#{n} bottles of beer on the wall, #{n} bottles of beer.\nTake one down and pass it around, #{n-1} bottles of beer on the wall.\n"
		end
	end

	def verses(starting, ending)
		result = String.new
		starting.downto(ending) do |i|
			result += verse(i) + "\n"
		end
		result
	end

	def sing
		verses(99, 0)
	end

end
