class Bob
  def hey(message)
    @message = message
    case
      when silence?
        'Fine. Be that way!'        
      when yell?
        'Woah, chill out!'
      when question?
        'Sure.'
      else
        'Whatever.'
    end
  end

  def question?
    @message.end_with?('?')
  end

  def yell?
    @message.upcase == @message
  end

  def silence?
    @message.strip.empty?
  end
end
