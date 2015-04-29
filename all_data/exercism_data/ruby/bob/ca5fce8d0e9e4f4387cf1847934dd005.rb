class Bob
  
  attr_reader :message

  def initialize
    @message = Message.new
  end
  
  def hey(phrase)
    if message.silence(phrase)
      'Fine. Be that way!'
    elsif message.shouting(phrase)
      'Woah, chill out!'
    elsif message.question(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message

  def shouting(phrase)
    phrase.match(/!$/) && !phrase.match(/([a-z^ ])+!/) || phrase.match(/^[A-Z^ ]+\?$/) || phrase.match(/^[A-Z^ ]+$/) ? true : false
  end

  def question(phrase)
    phrase.match(/\?$/) && !phrase.match(/\n^.+$/) ? true : false
  end

  def silence(phrase)
    (phrase.match(/^$/) || phrase.match(/^[ ]+$/)) && !phrase.match(/\n^.+$/) ? true : false
  end

end
