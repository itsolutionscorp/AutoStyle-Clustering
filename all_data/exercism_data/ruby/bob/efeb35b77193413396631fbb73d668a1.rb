class Bob
  def hey(input)
    
    message = ProcessedMessage.new(input)

    case 
    when message.silence?
     'Fine. Be that way!'
    when message.shouting?
     'Woah, chill out!'
    when message.asks_a_question?
     'Sure.'
    else 'Whatever.'
    end
    
  end

end



class ProcessedMessage
  attr_reader :message

  def initialize(input)
    @message = input.strip
  end

  def silence?
    message == ''
  end

  def shouting?
    message == message.upcase 
  end

  def asks_a_question?
    message.end_with?('?')
  end

end
