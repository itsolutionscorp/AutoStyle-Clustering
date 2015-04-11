class Bob
  def hey(message_text)
    message = Message.new(message_text)
    return 'Woah, chill out!' if message.shouting?
    return 'Sure.' if message.asking?
    return 'Fine. Be that way!' if message.silence?
    return 'Whatever.'
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
