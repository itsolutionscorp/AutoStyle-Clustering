class Bob
  def hey(msg)
    if said_nothing?(msg)
      "Fine. Be that way."
    elsif shouty?(msg)
      "Woah, chill out!"
    elsif questioning?(msg)
      "Sure."
    else
      "Whatever."
    end
  end

private

  def said_nothing?(msg)
    msg.to_s.empty?
  end

  def shouty?(msg)
    msg.upcase == msg
  end

  def questioning?(msg)
    msg.end_with?("?")
  end

end
