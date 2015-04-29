class Bob
	def hey(what_he_said)
		case what_he_said
		when /^\s*$/
			'Fine. Be that way!'
		when what_he_said.upcase
			'Woah, chill out!'
		when /\?$/
			'Sure.'
		else
			'Whatever.'
		end
	end
end
