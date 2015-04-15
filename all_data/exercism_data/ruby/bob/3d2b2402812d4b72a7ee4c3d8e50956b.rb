class Bob
	def hey(something)
		return 'Fine. Be that way!' if something == nil || something.strip === ''
		return 'Woah, chill out!' if something === something.upcase
		return 'Whatever.' if something[-1] === '.' || something[-1] == '!'
		return 'Sure.' if something[-1] === '?'
	end
end
