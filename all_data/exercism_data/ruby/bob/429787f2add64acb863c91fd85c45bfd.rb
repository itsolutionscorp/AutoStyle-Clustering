class Bob
  attr_reader :replies

  def initialize
    @replies = {
      shouting: "Woah, chill out!",
      question: "Sure.",
      silence: "Fine. Be that way!",
      default: "Whatever."
    }
  end

	def hey(msg)
    replies[Message.new(msg).check]
  end
end

class Message
  attr_reader :message

  def initialize(msg)
    @message = msg.to_s
  end

  def check
    return :silence  if silence?
    return :shouting if shouting?
    return :question if question?
    return :default
  end

private

  def shouting?
    message == message.upcase
  end

  def question?
    message.end_with? '?'
  end

  def silence?
    message.length == 0
  end

end