class Bob 
  def hey(msg)
    if is_silence? msg
      return 'Fine. Be that way!'
    end

    if is_yelling? msg
      return 'Woah, chill out!'
    end

    if is_asking? msg
      return 'Sure.'
    end

    'Whatever.'
  end

  def is_silence?(msg)
    msg.strip == ''
  end

  def is_yelling?(msg)
    msg.upcase == msg
  end

  def is_asking?(msg)
    msg.end_with? '?'
  end 

  private :is_silence?, :is_yelling?, :is_asking?
end
