module Message
	def self.nothing?(message)
		message.to_s.strip.empty?	
	end

  def self.yelling?(message)
    message.upcase == message
  end

  def self.question?(message)
    message.end_with? '?'
  end

	def self.always_true(message)
		true
	end
end

class Bob
  def initialize(behaviors = {
      Message.method(:nothing?) => 'Fine. Be that way!',
      Message.method(:yelling?) => 'Woah, chill out!',
      Message.method(:question?) => 'Sure.',
      Message.method(:always_true) => "Whatever."
    })
    @behaviors = behaviors
  end

  def hey(message)
    @behaviors.fetch @behaviors.keys.find{|predicate| predicate.call(message)}
  end
end
