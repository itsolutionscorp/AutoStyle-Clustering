class Bob
	def hey(talk)
		message = Message.new(talk)

		case 
		when message.nothing_said?
			"Fine. Be that way!"
		when message.yelled_at_him?
			"Woah, chill out!"
		when message.question_asked?
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

	def question_asked?
		@talk.end_with? "?"
	end

	def yelled_at_him?
		@talk.upcase.eql? @talk
	end
end
