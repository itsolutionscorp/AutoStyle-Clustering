class BeerSong

	def initialize
		
	end

	def verse(n)
		firstLine(n) << secondLine(n)
	end

	def verses(start, stop)
		(start).downto(stop).map { |i| verse(i) }.join("\n") << "\n"
	end

	def sing
		verses(99, 0)
	end

	private

		def firstLine(n)
			"#{bottles(n)} of beer on the wall, #{bottles(n).downcase} of beer.\n"
		end

		def secondLine(n)
			case n
			when 0
				"Go to the store and buy some more, 99 bottles of beer on the wall.\n"
			else
				"Take #{takeWhatDown(n)} down and pass it around, #{bottles(n-1).downcase} of beer on the wall.\n"
			end
		end

		def bottles(n)
			case n
			when 1
				"1 bottle"
			when 0
				"No more bottles"
			else
				"#{n} bottles"
			end
		end

		def takeWhatDown(n)
			case n
			when 1
				"it"
			else
				"one"
			end
		end

end
