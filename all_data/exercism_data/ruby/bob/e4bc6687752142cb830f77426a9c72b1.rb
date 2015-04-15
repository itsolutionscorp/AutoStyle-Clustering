# Bob answers 'Sure.' if you ask him a question.
# He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
# He says 'Fine. Be that way!' if you address him without actually saying anything.
# He answers 'Whatever.' to anything else.

class Bob
	def hey(message)
		return 'Fine. Be that way!' if message.strip.empty?
		return 'Woah, chill out!' if message == message.upcase
		return 'Sure.' if message.end_with? '?'
		'Whatever.'
	end
end
