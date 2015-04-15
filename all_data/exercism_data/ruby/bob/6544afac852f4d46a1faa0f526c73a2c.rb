class Message
  def initialize(msg)
    @msg = msg
  end

  def is_shouting?
    @msg.upcase == @msg and @msg.downcase != @msg
  end

  def is_question?
    @msg.end_with? '?'    
  end

  def is_awkward_silence?
    @msg.strip.empty?
  end
end

class Bob  
  def hey(msg)
    msg = Message.new(msg)

    return 'Woah, chill out!' if msg.is_shouting?
    return 'Sure.' if msg.is_question?
    return 'Fine. Be that way!' if msg.is_awkward_silence?
    return 'Whatever.'
  end
end
