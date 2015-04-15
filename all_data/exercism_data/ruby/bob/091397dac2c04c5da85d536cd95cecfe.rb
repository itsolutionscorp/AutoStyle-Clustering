class Bob
  attr_reader :message
  def hey(message)
    message = Message.new(message)
    if message.is_empty?
      'Fine. Be that way!'
    elsif message.is_shouting?
      'Woah, chill out!'
    elsif message.is_a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def is_empty?
    message.to_s == ''
  end

  def is_shouting?
    message.upcase == message
  end

  def is_a_question?
    /\?$/.match message
  end
end
