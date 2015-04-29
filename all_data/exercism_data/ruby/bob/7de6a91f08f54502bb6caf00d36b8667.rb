class Bob
  def hey(message)
    
    @message = Message.new(message)

    if @message.silent?
      'Fine. Be that way!'
    elsif @message.shouting?
      'Woah, chill out!'
    elsif @message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(message)
    @message = message
  end

  def silent?
    !@message || @message.length === 0
  end

  def shouting?
    @message == @message.upcase
  end

  def question?
    @message.end_with? '?'
  end
end
