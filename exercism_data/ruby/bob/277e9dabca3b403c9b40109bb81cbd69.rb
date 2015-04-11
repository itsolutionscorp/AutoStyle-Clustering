class Bob
  def hey(input)
    
    prompt = Message.new(input)

    case 
    when prompt.silence?
     'Fine. Be that way!'
    when prompt.shouting?
     'Woah, chill out!'
    when prompt.asks_a_question?
     'Sure.'
    else 'Whatever.'
    end
    
  end

end



class Message
  attr_reader :prompt

  def initialize(input)
    @prompt = input.strip
  end

  def silence?
    prompt == ''
  end

  def shouting?
    prompt == prompt.upcase 
  end

  def asks_a_question?
    prompt.end_with?('?')
  end

end
