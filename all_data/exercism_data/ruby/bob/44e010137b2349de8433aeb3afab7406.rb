class Bob
  attr_reader :message
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
    @message = msg.to_s
    return replies[:shouting] if shouting?
    return replies[:question] if question?
    return replies[:silence]  if silence?
    return replies[:default]
  end

  def shouting?
    !silence? && message == message.upcase
  end

  def question?
    message.end_with? '?'
  end

  def silence?
    message.length == 0
  end

end
