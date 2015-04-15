class Bob
	
	def hey(message)
		message = Message.new(message)
		
		case
		when message.saying_nothing?
			"Fine. Be that way!"
		when message.yelling?
			"Whoa, chill out!"
		when message.question?
			"Sure."
		else
			"Whatever."
		end

	end

	private

		class Message < String
			
			def saying_nothing?
				self !~ /\S/
			end

			def yelling?
				self =~ /[A-Z]/ && self !~ /[a-z]/
			end

			def question?
				self =~ /\?\z/
			
			end
		end

end
