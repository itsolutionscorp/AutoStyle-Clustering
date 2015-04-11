class Bob

  def hey(message)
    
    @message = Message.new(message)

    if @message.is_empty?
      'Fine. Be that way!'
    elsif @message.is_uppercase?
      'Woah, chill out!'
    elsif @message.is_question?
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

  def is_empty?
    !@message || @message.length === 0
  end

  def is_uppercase?
    @message == @message.upcase
  end

  def is_question?
    @message.end_with? '?'
  end

end
