class Bob
	def hey(what_he_said)
		raise 'nothing said' if what_he_said.nil?
		case
		when what_he_said =~ /^\s*$/
			'Fine. Be that way!'
		when what_he_said =~ /[A-Z]/ && what_he_said == what_he_said.upcase
			'Woah, chill out!'
		when what_he_said =~ /\?$/
			'Sure.'
		else
			'Whatever.'
		end
	end
end
