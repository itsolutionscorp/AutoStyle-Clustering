class Bob
  def exclamation?(msg)
    msg.upcase == msg
  end

  def question?(msg)
    msg.end_with? '?'
  end

  def silence?(msg)
    msg.to_s.empty?
  end

  def hey(msg)
    if silence? msg
      'Fine. Be that way.'
    elsif exclamation? msg
      'Woah, chill out!'
    elsif question? msg
      'Sure.'
    else
      'Whatever.'
    end
  end
end
