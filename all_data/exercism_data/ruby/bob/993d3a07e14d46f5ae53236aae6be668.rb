class Message
	def initialize(text)
		@text = text
	end

	def blank?
		@text.to_s.strip == ''
	end

	def shouting?
		@text == @text.upcase
	end

	def question?
		@text.end_with? '?'
	end
end
