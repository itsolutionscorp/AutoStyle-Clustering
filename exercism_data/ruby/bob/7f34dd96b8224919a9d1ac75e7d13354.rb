class Bob
	def hey(x)
		silence?(x) || yelling?(x) || inquisitive?(x) || 'Whatever.'
	end

	def yelling?(x)
		'Woah, chill out!' if x.upcase == x
	end

	def inquisitive?(x)
		'Sure.' if x.end_with? '?'
	end

	def silence?(x)
		'Fine. Be that way!' if x.nil? || x.empty?
	end
end
