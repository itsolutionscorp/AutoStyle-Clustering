class Bob
  def hey(msg)
    message = msg.to_s
    if shouting?(message)
      "Woah, chill out!"
    elsif questioning?(message)
      "Sure."
    elsif silent?(message)
      "Fine. Be that way."
    else
      "Whatever."
    end
  end

  private

  def shouting?(msg)
    msg.upcase === msg && !silent?(msg)
  end

  def questioning?(msg)
    msg.end_with? "?"
  end

  def silent?(msg)
    msg.length == 0
  end
end
