class Bob
	def hey (str)
		/\S/.match(str) or
			return 'Fine. Be that way!'
		/\p{Upper}/.match(str) and !/\p{Lower}/.match(str) and
			return 'Woah, chill out!'
		/\?\z/.match(str) and
			return 'Sure.'
		return 'Whatever.'
	end
end
