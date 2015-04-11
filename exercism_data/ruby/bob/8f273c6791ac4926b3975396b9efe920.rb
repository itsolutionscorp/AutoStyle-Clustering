class Bob
	def hey(statement)
		case statement || ""
		when silence
			'Fine. Be that way!'
		when shouting
			'Woah, chill out!'
		when question
			'Sure.'
		else
		  'Whatever.'
	  end
	end

  private

	def shouting ; /\A[^[:lower:]]+\Z/ ; end
	def question ; /\?\Z/ ; end
	def silence ; /\A *\Z/ ; end

end
