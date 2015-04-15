class Bob
  def question? msg
    msg.end_with? "?"
  end
  def empty_message? msg
    !msg || msg.strip.empty?
  end
  def yelling? msg
    msg.upcase == msg
  end
  
  def hey(msg)
    @msg = msg
    if empty_message? msg
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
