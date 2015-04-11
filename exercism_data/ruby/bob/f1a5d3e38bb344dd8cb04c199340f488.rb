class Bob
	def hey (cadena)
		if cadena != String
			return 'Whatever.'
		end
		if cadena=~ /[A-Z]/
			return 'Woah, chill out!'
		end
		if cadena=~ /[a-z]/
			return 'Fine. Be that way!'
		end
		if cadena.end_with?('?')
			return 'Sure.'
		end
	end
end
