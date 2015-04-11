class Bob
  attr_reader :msg

  def hey(msg)
    @msg = msg
    answer
  end

  def answer
    if silence?
      'Fine. Be that way!'
    elsif yelling?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def meaningful?(message=msg)
    !! message.match(/[a-zA-Z]/)
  end

  def question?(message=msg)
    message.end_with?('?')
  end

  def yelling?(message=msg)
    meaningful?(message) && upcase?(message)
  end

  def silence?(message=msg)
    message.strip.empty?
  end

  def upcase?(message=msg)
    message.upcase == msg
  end

end
