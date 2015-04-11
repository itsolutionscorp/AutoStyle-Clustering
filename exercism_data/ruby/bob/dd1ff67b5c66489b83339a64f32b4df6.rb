class Bob

  def hey(msg)
    formulate_pithy_comeback(msg)
  end

  def formulate_pithy_comeback(msg)
    message = msg.to_s
    if message.upcase === message && message.length > 0
      "Woah, chill out!"
    elsif message.end_with? "?"
      "Sure."
    elsif message.length == 0
      "Fine. Be that way."
    else
      "Whatever."
    end
  end

end
