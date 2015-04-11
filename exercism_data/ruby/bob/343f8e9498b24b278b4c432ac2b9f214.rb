class Bob
  attr_accessor :message_to_bob

  def hey(message)
    @message_to_bob = message
    case
      when silence?
        reply = 'Fine. Be that way!'        
      when yell?
        reply = 'Woah, chill out!'
      when question?
        reply = 'Sure.'
      else
        reply = 'Whatever.'
    end
  end

  def question?
    @message_to_bob.end_with?('?')
  end

  def yell?
    @message_to_bob.upcase == @message_to_bob
  end

  def silence?
    @message_to_bob.strip.empty?
  end

end
