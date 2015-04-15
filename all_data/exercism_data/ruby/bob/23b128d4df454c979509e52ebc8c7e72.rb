class Bob
	def hey(x)
		case
		when silence?(x)
			'Fine. Be that way!'
		when yelling?(x)
			'Woah, chill out!'
		when inquisitive?(x)
			'Sure.'
		else
			'Whatever.'
		end
	end

	def yelling?(x)
		 x.upcase == x
	end

	def inquisitive?(x)
		x.end_with? '?'
	end

	def silence?(x)
		 x.nil? || x.empty?
	end
end
