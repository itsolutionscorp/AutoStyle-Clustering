class Bob 
  def hey(msg)
    reply(msg)
  end

  def reply message
    msg = Message.new(message)

    if msg.silence?
      'Fine. Be that way!'
    elsif msg.yelling?
      'Woah, chill out!'
    elsif msg.asking?
      'Sure.' 
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize msg
    @msg = msg
  end

  def silence?
    @msg.strip.empty?
  end

  def yelling?
    @msg.upcase == @msg and @msg.upcase != @msg.downcase
  end

  def asking?
    @msg.end_with? '?'
  end 

end
