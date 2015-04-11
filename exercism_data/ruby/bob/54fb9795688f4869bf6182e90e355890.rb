class Bob
  def hey msg
    return "Fine. Be that way!" if blank? msg
    return "Whoa, chill out!"   if command? msg
    return "Sure."              if msg.end_with? "?"
    "Whatever."
  end

  def blank? msg
    msg.chars.all? { |char| char =~ /\s/ }
  end

  def command? msg
    msg == msg.upcase and msg.chars.any? { |char| char =~ /[A-Z]/ }
  end
end
