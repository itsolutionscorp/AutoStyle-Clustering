class BeerSong
	def verse(bottles)
		first_part = "#{say(bottles).capitalize} on the wall, #{say(bottles)}.\n"
		if bottles > 0
			second_part = "Take #{take(bottles)} down and pass it around, #{say(bottles-1)} on the wall.\n"
		else
			second_part = "Go to the store and buy some more, #{say(99)} on the wall.\n"
		end
	first_part + second_part
	end
	
	def verses(from, to)
		(to..from).to_a.reverse.inject("") do |results, i|
			results += "#{verse(i)}\n"
		end
	end
	
	def sing
		verses(99, 0)
	end
	
private
	def say(number)
		return "no more bottles of beer" if number == 0
		return "1 bottle of beer" if number == 1
		return "#{number} bottles of beer"
	end
	
	def take(remaining)
		remaining == 1 ? "it" : "one"
	end
end
