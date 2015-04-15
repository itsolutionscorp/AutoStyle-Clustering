class Bob
  attr_accessor :message
  private :message

  def hey(what)
    self.message = Message.new(what)
    respond
  end

  def respond
    return 'Fine. Be that way!' if message.completely_empty? 
    return 'Woah, chill out!' if message.upcase? 
    return 'Sure.' if message.question? 
    "Whatever."
  end
end

class Message < Struct.new(:message)
  def upcase?
    message == message.upcase
  end

  def question?
    message.end_with? '?'
  end

  def completely_empty?
    message.strip.empty?
  end
end
