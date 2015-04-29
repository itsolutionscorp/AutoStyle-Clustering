class Bob
	def hey(talk)
		message = Message.new(talk)
		if message.nothing_said?
			"Fine. Be that way!"
		elsif message.yelled_at_him?
			"Woah, chill out!"
		elsif message.question_asked?
			"Sure."
		else
			"Whatever."
		end
	end
end

class Message

	def initialize(talk)
		@talk = talk
	end

	def nothing_said?
		@talk.nil? || @talk.strip.empty?
	end

	def question_asked?
		@talk.end_with? "?"
	end

	def yelled_at_him?
		@talk.upcase.eql? @talk
	end
end
