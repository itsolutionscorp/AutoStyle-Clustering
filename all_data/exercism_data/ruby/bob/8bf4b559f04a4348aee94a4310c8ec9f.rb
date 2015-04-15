class Bob
	def hey(ask)
		return 'Fine. Be that way!' if ask.strip.empty?
		return 'Woah, chill out!' if ask == ask.upcase
		return 'Sure.' if ask.chars.last == '?'
		"Whatever."
	end
end
