class Say

	CHUNK_NAMES = ["", "thousand", "million", "billion", "trillion"]
	NAMES = [%w(_ one two three four five six seven eight nine),
					 %w(_ ten twenty thirty forty fifty sixty seventy eighty ninety),
					 %w(_ eleven twelve thirteen fourteen fifteen sixteen seventeen eighteen nineteen)]
	def initialize(number)
		raise ArgumentError if (number < 0) || (number >= 1000000000000)
		@chunks = chunks(number).zip(CHUNK_NAMES)
	end
	
	def in_english
		@chunks.reverse.map do |chunk|
			"#{to_human(chunk[0])} #{chunk[0] == 0 ? "" : chunk[1]}".strip
		end.join(" ").squeeze(" ").strip
	end
	
private
	def chunks(number)
		number.to_s.reverse.scan(/[0-9]{1,3}/).map do |chunk|
			chunk.reverse.to_i
		end
	end
	
	def to_human(number)
		h = hundreds(number/100)
		r = rest(number.modulo(100))

		return "zero" if (number == 0) && (@chunks.count == 1)
		h + " " + r
	end
	
	def hundreds(digit)
		return "" if digit == 0
		return "%s hundred" % NAMES[0][digit]
	end
	
	def rest(number)
		case number
		when 0
			""
		when 1..9
			NAMES[0][number]
		when 10
			NAMES[1][1]
		when 11..19
			NAMES[2][number.modulo(10)]
		when 20, 30, 40, 50, 60, 70, 80, 90
			NAMES[1][number/10]
		else
			"#{NAMES[1][number/10]}-#{NAMES[0][number.modulo(10)]}"
		end
	end
end
