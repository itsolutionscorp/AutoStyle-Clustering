class Bob
	def hey(statement)
		case statement
		when silence, nil
			'Fine. Be that way!'
		when shouting
			'Woah, chill out!'
		when question
			'Sure.'
		else
		  'Whatever.'
	  end
	end

	def shouting ; /^[^[:lower:]]+$/ ; end
	def question ; /^.*\?$/ ; end
	def silence ; /^ *$/ ; end

end
