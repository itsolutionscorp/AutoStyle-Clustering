class Bob

  def hey(msg)
    formulate_pithy_comeback(msg)
  end

  def formulate_pithy_comeback(msg)
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

  def shouting?(msg)
    msg.upcase === msg && msg.length > 0
  end

  def questioning?(msg)
    msg.end_with? "?"
  end

  def silent?(msg)
    msg.length == 0
  end

end
