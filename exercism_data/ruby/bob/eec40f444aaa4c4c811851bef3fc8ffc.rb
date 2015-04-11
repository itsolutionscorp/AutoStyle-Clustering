class Bob

	def hey(text)
		case process_conversation(text)
		when :silence
			"Fine. Be that way!"
		when :shouting
			"Woah, chill out!"
		when :question
			"Sure."
		when :default
			"Whatever."
		end
	end

	private

	def process_conversation(text)
		ConversationParser.new(text).conversation_type
	end

end

class ConversationParser

	def initialize(text)
		self.text = text
	end

	def conversation_type
		return :silence if silence?
		return :shouting if shouting?
		return :question if question?
		return :default
	end

	private

	attr_accessor :text

	def silence?
		text.to_s.strip == ""
	end

	def shouting?
		text.upcase == text
	end

	def question?
		text.end_with? "?"
	end

end
