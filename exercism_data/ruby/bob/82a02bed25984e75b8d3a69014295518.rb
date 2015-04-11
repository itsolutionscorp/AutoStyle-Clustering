class Message
  def initialize(msg)
    @msg = msg
  end

  def shouting?
    @msg.upcase == @msg and @msg.downcase != @msg
  end

  def question?
    @msg.end_with? '?'    
  end

  def awkward_silence?
    @msg.strip.empty?
  end
end

class Bob
  def hey(msg)
    msg = Message.new(msg)

    return 'Woah, chill out!' if msg.shouting?
    return 'Sure.' if msg.question?
    return 'Fine. Be that way!' if msg.awkward_silence?
    return 'Whatever.'
  end
end
