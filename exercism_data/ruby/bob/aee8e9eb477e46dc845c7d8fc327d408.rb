class Message
  def initialize(message)
    @message = message
  end

  def blank?
    @message =~ /^\s*$/
  end

  def shouting?
    @message == @message.upcase
  end

  def question?
    @message[-1] == '?'
  end
end

class Bob
  def hey(message)
    message = Message.new(message)
    if message.blank?
      'Fine. Be that way!'
    elsif message.shouting?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
