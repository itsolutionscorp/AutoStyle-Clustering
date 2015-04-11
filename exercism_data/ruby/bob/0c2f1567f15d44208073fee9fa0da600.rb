class Bob
	def hey(ask)
		case
		when empty_or_nil(ask)
			'Fine. Be that way.'
		when shouting(ask)
			'Woah, chill out!'
		when ends_with_question(ask)
			'Sure.'
		else
			'Whatever.'
		end
	end

	def empty_or_nil(ask)
		ask.nil? || ask.empty?
	end

	def shouting(ask)
		ask.upcase == ask
	end

	def ends_with_question(ask)
		ask.end_with?("?")
	end
end
