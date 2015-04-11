class String
  def to_message
    Message.new(self)
  end
end

class NilClass
  def to_message
    Message.new(nil)
  end
end

class Message
  def initialize(message)
    @message = message
  end

  def silent?
    @message.nil? or @message.strip.size < 1
  end

  def shout?
    @message.upcase == @message
  end

  def question?
    @message[-1] == '?'
  end
end

class Bob
  def hey(message)
    message = message.to_message
    if message.silent?
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
