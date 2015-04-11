# He answers 'Sure.' if you ask him a question. He answers 'Whatever.' if you tell him something. He answers 'Woah, chill out!' if you yell at him (ALL CAPS). He says 'Fine. Be that way!' if you address him without actually saying anything.

class Bob



	def self.respond (message)
		if message.include?('?')
			return "Sure."
		elsif message.empty?
			return "Fine. Be that way!"
		elsif message == message.upcase
			return "Woah, chill out!"
		else
			return "Whatever."
		end
	end
end
