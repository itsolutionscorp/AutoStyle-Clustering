RESPONSES = {
	:question => 'Sure.',
        :yell     => 'Woah, chill out!',
	:silence  => 'Fine. Be that way!',
        :default  => 'Whatever.'
}

class String
	def is_question
		strip =~ /\?$/
	end

	def is_yell
		upcase == self
	end

	def is_silence
		strip == ''
	end

	def message_type
		return :silence  if is_silence
		return :yell     if is_yell
		return :question if is_question
		:default
	end
end

class Bob
	def hey(value)
		RESPONSES[value.to_s.message_type]
	end
end