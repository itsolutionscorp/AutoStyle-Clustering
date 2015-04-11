class Bob
	def hey (say_what)
		words = Words.new say_what
		if words.silent?
			'Fine. Be that way!'
		elsif words.shouting?
			'Woah, chill out!'
		elsif words.question?
			'Sure.'
		else
			'Whatever.'
		end
	end
end
class Words
	def initialize (message)
		@message = message
	end
	def silent?
		@message =~ /\A\s*\Z/
	end
	def shouting?
		@message.eql? @message.upcase
	end
	def question?
		@message.end_with? '?'
	end
end
