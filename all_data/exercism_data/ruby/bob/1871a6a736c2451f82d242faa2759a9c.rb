class Bob
  def hey(msg)
    message = Message.new(msg)
    case
    when message.shouting? then 'Woah, chill out!'
    when message.question? then 'Sure.'
    when message.silent? then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end
end

class Message
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def shouting?
    message =~ /[A-Z]/ && message == message.upcase
  end

  def question?
    message.end_with?('?')
  end

  def silent?
    message.strip.empty?
  end
end
