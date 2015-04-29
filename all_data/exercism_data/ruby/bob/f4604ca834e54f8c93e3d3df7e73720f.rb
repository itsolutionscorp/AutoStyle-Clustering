class Bob
    
  def hey(phrase)
    @message = Message.new(phrase)
    if message.silence?
      'Fine. Be that way!'
    elsif message.shouting?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message

  def initialize(phrase)
   @phrase = phrase
  end

  def shouting?
    phrase == phrase.upcase
  end

  def question?
    phrase[-1] == '?'
  end

  def silence?
    phrase.strip == ''
  end

end
