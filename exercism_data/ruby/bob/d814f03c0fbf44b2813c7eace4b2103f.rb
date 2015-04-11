class Bob
	def hey(phrase)
		case phrase
		when '', nil
			'Fine. Be that way!'
		when /\A[A-Z\d\W]+\Z/
			'Woah, chill out!'
		when /\?\Z/
			'Sure.'
		else
			'Whatever.'
		end
	end
end
