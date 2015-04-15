# Bob answers 'Sure.' if you ask him a question.
# He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
# He says 'Fine. Be that way!' if you address him without actually saying anything.
# He answers 'Whatever.' to anything else.

class String
	def yelling?
		!blank? and self == self.upcase
	end

	def question?
		!yelling? and self =~ /\?$/
	end

	def blank?
		self !~ /[^[\s]]/
	end
end

class Bob
	def hey(message)
		return 'Woah, chill out!' if message.yelling?
		return 'Sure.' if message.question?
		return 'Fine. Be that way!' if message.blank?
		'Whatever.'
	end
end
