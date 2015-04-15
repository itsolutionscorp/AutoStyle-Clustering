class Bob

	def hey message	
		message = Message.new message

		# note: .shout? must be before .simple_question?
		answer = case
			       when message.empty? then 'Fine. Be that way!'
			       when message.shout? then 'Woah, chill out!'
			       when message.simple_question? then 'Sure.'
			       else "Whatever."
			       end

		answer
	end
 
end

class Message

	def initialize message
		@message = message
	end

	def empty?
		
		if @message.empty?
			true
		else
			(/[^\s]/ =~ @message) == nil
		end
	end

	def shout?

		if /[a-zA-Z]+/ =~ @message
			@message.upcase == @message
		else
			false
		end

	end

	def simple_question?
		@message[-1] == '?'
	end

end
