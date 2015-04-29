class Bob
  def hey(msg)
    return "Whoa, chill out!"   if shouting?(msg)
    return "Sure."              if question?(msg)
    return "Fine. Be that way!" if blank?(msg)
    "Whatever."
  end

  def shouting?(msg)
    msg == msg.upcase && msg[/[A-Z]/]
  end

  def question?(msg)
    msg.end_with?('?')
  end

  def blank?(msg)
    msg.strip.size == 0
  end
end
