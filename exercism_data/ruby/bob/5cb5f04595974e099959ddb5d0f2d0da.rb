class Bob
  def hey(msg)
    if silence?(msg)
      'Fine. Be that way.'
    elsif shouting?(msg)
      'Woah, chill out!'
    elsif question?(msg)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def question?(msg)
    msg[-1] == '?'
  end

  def shouting?(msg)
    msg == msg.upcase
  end

  def silence?(msg)
    msg.to_s.empty?
  end
end
