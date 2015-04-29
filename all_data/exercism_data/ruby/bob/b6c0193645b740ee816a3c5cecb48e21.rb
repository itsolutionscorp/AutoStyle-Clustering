class Bob
  def hey(message)
    message = Message.new(message)
    if message.shouted?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    elsif message.nothing_said?
      'Fine. Be that way!'
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

  def shouted?
    message[/[a-zA-Z]+/] && message.upcase == message 
  end

  def question?
    message.end_with?('?')
  end
  
  def nothing_said?
    message[/\A\s*\Z/]
  end
end
