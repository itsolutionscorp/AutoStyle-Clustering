class Bob

  def hey(message)
    message_content = MessageContent.new(message)

    case
    when message_content.silence?
      'Fine. Be that way.'
    when message_content.question?
      'Sure.'
    when message_content.shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

end

class MessageContent

  def initialize(message)
    @message = message.to_s
  end

  def silence?
    @message.strip.empty?
  end

  def question?
    @message.end_with?('?')
  end

  def shouting?
    @message.upcase == @message
  end

end
