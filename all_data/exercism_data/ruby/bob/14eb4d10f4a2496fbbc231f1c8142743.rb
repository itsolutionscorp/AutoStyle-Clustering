class Bob
	def initialize(behaviors = {
      -> (message) { message.nil? || message.strip.empty?} => 'Fine. Be that way!',
			-> (message) { message.upcase == message} => 'Woah, chill out!',
			-> (message) { message[-1].chr == '?'} => 'Sure.'
		})
		@behaviors = behaviors
	end

	def hey(message)
		@behaviors.fetch @behaviors.keys.find{|p| p.call message}, "Whatever."
	end
end
