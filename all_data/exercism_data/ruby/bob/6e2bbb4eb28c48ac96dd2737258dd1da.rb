class Bob
  def yelling?(msg)
    msg.upcase == msg
  end

  def question?(msg)
    msg.end_with?('?')
  end

  def silent?(msg)
    msg.nil? or msg.empty?
  end

  def hey(msg)
    if silent?(msg)
      'Fine. Be that way.'
    elsif yelling?(msg)
      'Woah, chill out!'
    elsif question?(msg)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
