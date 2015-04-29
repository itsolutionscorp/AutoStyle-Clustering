class Message
  def initialize(message)
    @message = message.strip
  end

  def silence?
    return @message.empty?
  end

  def question?
    return @message.end_with?('?')
  end

  def shouting?
    return @message == @message.upcase
  end
end

class TeenageBrain
  def response_to(message)
    return "Fine. Be that way!" if message.silence?
    return "Woah, chill out!"   if message.shouting?
    return "Sure."              if message.question?
    return "Whatever."        # if anything else
  end
end

class Bob
  def initialize(brain_type = TeenageBrain)
    @brain = brain_type.new
  end

  def hey(str)
    return @brain.response_to(Message.new(str))
  end
end
