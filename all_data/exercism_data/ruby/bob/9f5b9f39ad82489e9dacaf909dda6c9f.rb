class Bob
	def hey(statement)
		case statement[-1, 1]
			when '.'
				'Whatever.'
			when '!'
				'Whoa, chill out!'
			when '?'
				'Sure.'
			else
				'Fine. Be that way!'
		end
	end	
end
