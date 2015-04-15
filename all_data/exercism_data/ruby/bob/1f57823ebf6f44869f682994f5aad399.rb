class Bob

  def hey(message)
    case
    when empty?(message) then "Fine. Be that way."
    when shouting?(message) then "Woah, chill out!"
    when asking_a_question?(message) then "Sure."
    else "Whatever."
    end
  end

private
  def empty?(message)
    message.to_s == ""
  end

  def shouting?(message)
    message.upcase == message
  end

  def asking_a_question?(message)
    message.end_with? '?'
  end

end
