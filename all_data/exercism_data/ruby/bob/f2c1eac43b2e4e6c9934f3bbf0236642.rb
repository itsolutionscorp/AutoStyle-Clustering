class Bob
	def hey(ask)
		return 'Fine. Be that way!' if ask.strip.empty?
		return 'Woah, chill out!' if upcase?(ask)
		return 'Sure.' if ask.end_with? '?'
		"Whatever."
	end

	def upcase?(str)
		str == str.upcase
	end
end
