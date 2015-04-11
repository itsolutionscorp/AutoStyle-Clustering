class Bob
  def hey(message)
    respond_to(message)
  end

  def respond_to(message)
    decorated_message = MessageDecorator.new(message)

    if decorated_message.silent?
      'Fine. Be that way.'
    elsif decorated_message.loud?
      'Woah, chill out!'
    elsif decorated_message.questioning?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class MessageDecorator
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

  def method_missing(method, *args)
    args.empty? ? message.send(method) : message.send(method, args)
  end
end
