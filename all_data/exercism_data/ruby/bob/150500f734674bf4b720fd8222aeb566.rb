class Bob
  def hey(msg)
    return 'Fine. Be that way!' if nothing_said?(msg)
    return 'Woah, chill out!'   if yelling?(msg)
    return 'Sure.'              if asked_a_question?(msg)
    'Whatever.'
  end

  def nothing_said?(msg)
    msg =~ /\A\s*\Z/
  end

  def yelling?(msg)
    msg == msg.upcase
  end

  def asked_a_question?(msg)
    msg =~ /\?\Z/
  end
end
