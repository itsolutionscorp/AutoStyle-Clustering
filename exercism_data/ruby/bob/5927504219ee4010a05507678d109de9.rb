class Bob
  def hey(input_message)
    message = Message.new(input_message)
    return 'Fine. Be that way!' if message.is_empty?
    return 'Woah, chill out!'   if message.is_caps?
    return 'Sure.'              if message.is_question?
    'Whatever.'
  end
end

class Message
  def initialize(message)
    @message = message
  end

  def is_empty?
    @message.strip.empty?
  end

  def is_caps?
    @message == @message.upcase
  end

  def is_question?
    @message.chars.last == '?'
  end
end
