class Bob
	def hey statement
		return 'Woah, chill out!' if shouting? statement
		return 'Sure.' if question? statement
		return 'Fine. Be that way!' if silence? statement
		"Whatever."
	end

	def shouting? statement
		return false unless /[A-Za-z]/ =~ statement
		statement == statement.upcase
	end

	def question? statement
		statement[-1,1] == "?"
	end

	def silence? statement
		no_silence = /\S/ =~ statement
		!no_silence
	end
end
