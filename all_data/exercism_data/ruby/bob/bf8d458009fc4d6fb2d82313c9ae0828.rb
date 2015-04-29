class Bob
  def hey(message)
    message = Message.new(message)
    return 'Sure.'              if message.question? 
    return 'Fine. Be that way.' if message.empty?
    return 'Woah, chill out!'   if message.caps_lock? 
    return 'Whatever.'
  end
end

class Message
  def initialize(value)
    @value = value.to_s
  end

  def empty?
    @value.empty?
  end

  def question?
    @value.end_with? '?'
  end

  def caps_lock?
    @value.upcase == @value
  end
end
