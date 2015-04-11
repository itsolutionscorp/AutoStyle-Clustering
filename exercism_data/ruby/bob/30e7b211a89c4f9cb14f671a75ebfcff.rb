class Bob
  def hey(input)
    
    message = Message.new(input)

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



class Message
  attr_reader :message

  def initialize(input)
    @message = input
  end

  def silence?
    message == '' || message.chars.all? { |e|  e == ' '}
  end

  def shouting?
    message == message.upcase 
  end

  def asks_a_question?
    message.end_with?('?')
  end

end
