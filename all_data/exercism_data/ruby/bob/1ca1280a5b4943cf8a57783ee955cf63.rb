class Bob
	def hey statement
		respond_to Statement.new(statement)
	end

	private
	RESPOND_TO = {
		question: "Sure.",
		force: "Woah, chill out!",
		silence: "Fine. Be that way!",
		default: "Whatever."
	}

	def respond_to statement
		RESPOND_TO[statement.type]
	end
end

class Statement
	def initialize input
		@input = input
	end

	def type
		return :silence if is_silent
		return :force if is_force
		return :question if is_question
		:default
	end

	private
	def is_question
		@input.end_with? "?"
	end

	def is_force
		return unless @input.match(/[a-zA-Z]/)
		@input.upcase == @input
	end

	def is_silent
		@input.to_s.strip.empty?
	end
end
