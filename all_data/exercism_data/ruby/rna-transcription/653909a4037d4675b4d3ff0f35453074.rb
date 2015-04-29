class Bob
  def hey(message)
    respond_to(message)
  end

  def respond_to(message)
    message = WrappedMessage.new(message)

    case
    when message.silent?      then 'Fine. Be that way.'
    when message.loud?        then 'Woah, chill out!'
    when message.questioning? then 'Sure.'
    else 'Whatever.'
    end
  end
end

class WrappedMessage
  attr_accessor :message

  def initialize(message)
    self.message = message.to_s
  end

  def questioning?
    message.end_with? '?'
  end

  def silent?
    message.empty?
  end

  def loud?
    message == message.upcase
  end
end
