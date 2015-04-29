class Bob
	def hey(something)
		return 'Fine. Be that way!' if something.to_s.strip == ''
		return 'Woah, chill out!' if something == something.upcase
		return 'Sure.' if something.end_with? '?'

		'Whatever.'
	end
end
