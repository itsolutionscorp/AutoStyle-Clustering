class Bob
	def hey(something)
		return 'Fine. Be that way!' if something == nil || something.strip == ''
		return 'Woah, chill out!' if something == something.upcase
		return 'Sure.' if something[-1] == '?'

		'Whatever.'
	end
end