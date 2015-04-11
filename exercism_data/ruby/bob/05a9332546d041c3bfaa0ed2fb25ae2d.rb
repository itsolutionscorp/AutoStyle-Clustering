class Bob
  def hey(convo)
    case 
    when silence?(convo)
      nothing
    when yelling?(convo)
      yell
    when question?(convo)
      ask
    else
      tell
    end
  end

  private

  def silence?(convo)
    (convo == "" || convo.nil?)
  end

  def yelling?(convo)
    convo == convo.upcase 
  end

  def question?(convo)
    convo.end_with?("?") 
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
