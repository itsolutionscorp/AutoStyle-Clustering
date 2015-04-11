class Bob
  def hey(value)
    message = Message.new(value)
    
    if message.question? 
      'Sure.'
    elsif message.empty?
      'Fine. Be that way.'
    elsif message.shouted? 
      'Woah, chill out!'
    else
      'Whatever.'
    end
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

  def shouted?
    @value.upcase == @value
  end
end
