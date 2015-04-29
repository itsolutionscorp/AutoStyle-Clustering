class TwelveDaysSong
	
	LINES = [
		"%sa Partridge in a Pear Tree.\n",
		"two Turtle Doves, ",
		"three French Hens, ",
		"four Calling Birds, ",
		"five Gold Rings, ",
		"six Geese-a-Laying, ",
		"seven Swans-a-Swimming, ",
		"eight Maids-a-Milking, ",
		"nine Ladies Dancing, ",
		"ten Lords-a-Leaping, ",
		"eleven Pipers Piping, ",
		"twelve Drummers Drumming, "   
	]

	ORDINALS = %w{first second third fourth fifth sixth 
				seventh eighth ninth tenth eleventh twelfth}

	def sing
		verses(1, 12)
	end

	def verses(start, stop)
		(start..stop).map { |i| verse(i) }.join("\n") + "\n"
	end

	def verse(n)
		n = n-1
		v = "On the #{ORDINALS[n]} day of Christmas my true love gave to me, "
		v << lines(n)
		v % ( n > 0 ? "and " : nil )
	end

	private

		def lines(n)
			return LINES[0] if n == 0
			
			LINES[n] + lines(n-1)
		end

end
