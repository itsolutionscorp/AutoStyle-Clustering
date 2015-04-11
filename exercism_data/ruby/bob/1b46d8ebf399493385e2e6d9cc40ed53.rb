class Bob
  
  def hey(msg)
    msg = Message.new(msg)
    if msg.silent?
      'Fine. Be that way!'
    elsif msg.shouting?
      'Woah, chill out!'
    elsif msg.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end

class Message
  def initialize(msg)
    @msg = msg.respond_to?(:chr) ? msg : ''
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
