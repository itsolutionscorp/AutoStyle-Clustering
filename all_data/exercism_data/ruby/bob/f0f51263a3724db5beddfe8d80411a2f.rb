class Bob
  def hey(convo)
    case 
    when convo == "" || convo.nil?
      nothing
    when convo == convo.upcase
      yell
    when convo.end_with?("?")
      ask
    else
      tell
    end
  end

  def ask
    "Sure."
  end

  def tell
    "Whatever."
  end

  def yell
    "Woah, chill out!"
  end

  def nothing
    "Fine. Be that way."
  end

end
