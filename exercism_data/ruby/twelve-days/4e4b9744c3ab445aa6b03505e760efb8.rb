class TwelveDaysSong
	DAYS = 
	[
		['first', 'a Partridge in a Pear Tree.'],
		['second', 'two Turtle Doves'],
		['third', 'three French Hens'],
		['fourth', 'four Calling Birds'],
		['fifth', 'five Gold Rings'],
		['sixth', 'six Geese-a-Laying'],
		['seventh', 'seven Swans-a-Swimming'],
		['eighth', 'eight Maids-a-Milking'],
		['ninth', 'nine Ladies Dancing'],
		['tenth', 'ten Lords-a-Leaping'],
		['eleventh', 'eleven Pipers Piping'],
		['twelfth', 'twelve Drummers Drumming'],
	]
	
	def initialize()
	end
	
	def verse(num)
		num -= 1
		parts = ["On the #{DAYS[num][0]} day of Christmas my true love gave to me"]
		num.downto(0).each{|i| parts << DAYS[i][1]}
		parts[-1] = 'and ' + parts[-1] if parts.size > 2
		parts.join(', ') + "\n"
	end
	
	def verses(from, to)
		from.upto(to).collect{|i| verse(i)}.join("\n") + "\n"
	end
	
	def sing()
		verses(1, 12)
	end
end
