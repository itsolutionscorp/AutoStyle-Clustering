class Bob
  def hey(msg)
    return "Fine. Be that way!" if blank?(msg)
    return "Whoa, chill out!"   if forceful?(msg)
    return "Sure."              if msg.end_with?('?')
    "Whatever."
  end

  def forceful?(msg)
    msg == msg.upcase && 
    msg.chars.any? { |i| i =~ /[A-Z]/ }
  end

  def blank?(msg)
    msg.chars.all? { |i| i =~ /\s/ }
  end
end
