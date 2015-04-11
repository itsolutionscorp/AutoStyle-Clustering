class Bob
  def hey(message)
    if silent?(message)
      'Fine. Be that way.'
    elsif shouting?(message)
      'Woah, chill out!' 
    elsif asking?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def asking?(msg)
    msg.end_with? '?'
  end

  def shouting?(msg)
    msg.upcase == msg
  end

  def silent?(msg)
    msg.to_s.empty?
  end
end
