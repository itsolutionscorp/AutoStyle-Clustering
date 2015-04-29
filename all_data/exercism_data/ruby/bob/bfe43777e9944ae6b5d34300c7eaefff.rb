class Message
  def initialize msg
    @msg = msg
  end

  def is_silence?
    @msg.strip == ''
  end

  def is_yelling?
    @msg.upcase == @msg
  end

  def is_asking?
    @msg.end_with? '?'
  end 

  def reply
    if is_silence?
      'Fine. Be that way!'
    elsif is_yelling?
      'Woah, chill out!'
    elsif is_asking?
      'Sure.' 
    else
      'Whatever.'
    end
  end
end

class Bob 
  def hey(msg)
    Message.new(msg).reply
  end
end
