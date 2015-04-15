class Bob
  def hey(message)
    message = Message.new(message)

    if message.silence?
      'Fine. Be that way!'
    elsif message.shout?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  attr_reader :message

  def initialize(message)
    @message = message.to_s
  end

  def silence?
    message.empty?
  end

  def shout?
    message.upcase == message
  end

  def question?
    message.end_with? '?'
  end
end
