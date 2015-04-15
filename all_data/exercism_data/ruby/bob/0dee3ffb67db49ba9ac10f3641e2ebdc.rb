class Bob
	def hey(ask)
		return 'Fine. Be that way.' if empty_or_nil(ask)
		return 'Woah, chill out!' if shouting(ask)
		return 'Sure.' if ends_with_question(ask)
		return 'Whatever.'
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
