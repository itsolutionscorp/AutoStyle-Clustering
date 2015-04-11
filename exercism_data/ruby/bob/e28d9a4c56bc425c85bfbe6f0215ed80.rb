class Bob

  def question? msg
    msg.end_with? '?'
  end

  def yelling? msg
    msg.upcase == msg
  end

  def hey msg
    msg.strip!
    if msg.empty?
      "Fine. Be that way!"
    elsif yelling? msg
      "Woah, chill out!"
    elsif question? msg 
      "Sure."
    else 
      "Whatever."
    end 
  end

end
