class Bob
	def hey(talk)
		message = Message.new(talk)

		case 
		when message.nothing_said?
			"Fine. Be that way!"
		when message.shouted?
			"Woah, chill out!"
		when message.asked?
			"Sure."
		else
			"Whatever."
		end
	end
end

class Message
	def initialize(talk)
		@talk = String(talk)
	end

	def nothing_said?
		@talk.strip.empty?
	end

	def asked?
		@talk.end_with? "?"
	end

	def shouted?
		@talk.upcase == @talk
	end
end
