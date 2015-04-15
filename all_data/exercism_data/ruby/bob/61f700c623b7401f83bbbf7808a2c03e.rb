class Bob

  def hey(msg)
    m = Message.new(msg)
    
    if m.is_shouting?
      'Woah, chill out!'
    elsif m.is_a_question?
      "Sure."
    elsif m.is_silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end

class Message
  attr_reader :message

  def initialize(message)
    @message = message.gsub(/[\r\n]/,' ')
  end

  def is_silence?
    message.nil? || (message.strip == '')
  end

  def is_shouting?
    (message.upcase == message) && (/\D+/ =~ message.gsub(/[\s[:punct:]]/, ''))
  end

  def is_a_question?
    /\?$/ =~ message
  end

end
