class BeerSong
	def verse(bottle_count)
		@current_count = bottle_count
		"#{first_line}\n#{second_line}\n"
	end

	def verses(bottle_count_start, bottle_count_end)
		lyrics = []
		bottle_count_start.downto(bottle_count_end) do |count|
			lyrics << verse(count)
		end
		"#{lyrics.join("\n")}\n"
	end

	def sing
		verses(a_full_wall, 0)
	end

	private
	
	def first_line
		capitialize("#{bottles} of beer on the wall, #{bottles} of beer.")
	end

	def second_line
		if @current_count < 1
			"Go to the store and buy some more, #{a_full_wall} bottles of beer on the wall."
		else
			"Take #{one} down and pass it around, #{one_less_bottle} of beer on the wall."
		end
	end

	def capitialize(str)
		str.tap do |s|
			s[0] = s[0].upcase
		end
	end

	def bottles(amount = @current_count)
		case amount
		when 0 then "no more bottles"
		when 1 then "1 bottle"
		else "#{amount} bottles"
		end
	end

	def one_less_bottle
		bottles(@current_count-1)
	end

	def one
		@current_count == 1 ? "it" : "one"
	end

	def a_full_wall
		99
	end
end
