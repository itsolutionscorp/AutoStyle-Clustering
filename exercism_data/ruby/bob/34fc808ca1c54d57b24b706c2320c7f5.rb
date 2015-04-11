class Bob
	def hey(challenge)
		if challenge.nil? || challenge.empty?
			# they don't say anything at all
			response = 'Fine. Be that way.'
		elsif challenge.upcase == challenge
			# ups, somebody is yelling at me.
			response = 'Woah, chill out!'
		elsif challenge.match(/\?$/)
			# it's a question!
			response = 'Sure.'
		else
			response = 'Whatever.'
		end
		response
	end
end
