class Bob
  
  def hey(msg)
    msg = Message.new(msg)
    return 'Woah, chill out!' if msg.shouting? and not msg.silent?
    return 'Fine. Be that way!' if msg.silent?
    return 'Sure.' if msg.question? and not msg.shouting?
    'Whatever.'
  end

end

class Message
  def initialize(msg)
    @msg = msg.to_s
  end

  def shouting?
    @msg == @msg.upcase
  end

  def question?
    @msg.end_with?('?')
  end

  def silent?
    @msg.strip.empty?
  end
end
