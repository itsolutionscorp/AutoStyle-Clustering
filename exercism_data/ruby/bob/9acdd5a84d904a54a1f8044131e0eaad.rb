class Bob
  
  attr_reader :message
  
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

  attr_reader :phrase

  def initialize(phrase)
   @phrase = phrase
  end

  def shouting?
    phrase == phrase.upcase
  end

  def question?
    phrase.match(/\?$/) && !phrase.match(/\n^.+$/)
  end

  def silence?
    phrase.strip == ''
  end

end
