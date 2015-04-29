class Bob
	def hey(message)
		message = Message.new message

		case message
			when message.question? then 'Sure.'
			when message.shout? then 'Woah, chill out!'
			when message.empty? then 'Fine. Be that way!'
			else 'Whatever.'
		end
		
	end
end

class Message
	def initialize message
		@message = message
	end

	def question?
		@message.end_with?
	end

	def shout?
		@message.upcase == @message
	end

	def empty?
		@message.empty?
	end
end
