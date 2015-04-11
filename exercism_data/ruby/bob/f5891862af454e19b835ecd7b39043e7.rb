class Bob
	def hey(ask)
		ask = Ask.new(ask)
		return 'Fine. Be that way!' if ask.to_s.strip.empty?
		return 'Woah, chill out!' if ask.upcase?
		return 'Sure.' if ask.question?
		"Whatever."
	end
end

class Ask
	def initialize(ask)
		@ask = ask
	end

	def upcase?
		@ask == @ask.upcase
	end

	def question?
		@ask.end_with?('?')
	end

	def to_s
		@ask
	end
end
