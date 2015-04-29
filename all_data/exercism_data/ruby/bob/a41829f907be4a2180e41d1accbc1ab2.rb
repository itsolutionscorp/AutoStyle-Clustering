class Bob
  def hey(message_text)
    message = Message.new(message_text)
    case
    when message.shouting?
      'Woah, chill out!'
    when message.asking?
      'Sure.'
    when message.silence?
      'Fine. Be that way!' 
    else
      'Whatever.'
    end
  end
end

class Message
  attr_accessor :text
  
  def initialize(text)
    @text = text
  end
  
  def shouting?
    text.match /[A-Za-z]/ and text == text.upcase
  end
  
  def asking?
    text.end_with? '?'
  end
  
  def silence?
    text.gsub(/\s/, '') == ''
  end
end
