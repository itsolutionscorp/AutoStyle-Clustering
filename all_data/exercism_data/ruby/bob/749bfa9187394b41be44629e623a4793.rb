class Message
	def initialize(value)
		@value = value
	end

	def is_question?
		@value.strip.end_with?('?')
	end

	def is_yell?
		@value.upcase == @value
	end

	def is_silence?
		@value.strip == ''
	end

	def message_type
		return :silence  if is_silence?
		return :yell     if is_yell?
		return :question if is_question?
		:default
	end
end

class Bob
	RESPONSES = {
		:question => 'Sure.',
		:yell     => 'Woah, chill out!',
		:silence  => 'Fine. Be that way!',
		:default  => 'Whatever.'
	}

	def hey(value)
		RESPONSES[Message.new(value.to_s).message_type]
	end
end
