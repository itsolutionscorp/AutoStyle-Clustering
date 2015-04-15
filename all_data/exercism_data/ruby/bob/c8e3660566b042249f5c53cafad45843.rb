class Bob
	def hey(ask)
		if ask.nil? || ask.empty?
			'Fine. Be that way.'
		elsif ask.upcase == ask
			'Woah, chill out!'
		elsif ask.end_with?("?")
			'Sure.'
		else
			'Whatever.'
		end
	end
end
