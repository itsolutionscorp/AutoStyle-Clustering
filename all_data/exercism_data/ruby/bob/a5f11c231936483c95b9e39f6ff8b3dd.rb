class Bob
  def hey(message)
    respond_to(message)
  end

  def respond_to(message)
    message = WrappedMessage.new(message)

    if message.silent?
      'Fine. Be that way.'
    elsif message.loud?
      'Woah, chill out!'
    elsif message.questioning?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class WrappedMessage
  attr_accessor :message

  def initialize(message)
    self.message = message || ''
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
