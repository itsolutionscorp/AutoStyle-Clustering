class Bob
  def hey(msg)
    return 'Fine. Be that way!' if slience?(msg)
    return 'Woah, chill out!' if yelling?(msg)
    return 'Sure.' if questioning?(msg)
    'Whatever.'
  end

  def slience?(msg)
    msg.strip.empty?
  end

  def yelling?(msg)
    msg.upcase == msg && msg.downcase != msg
  end

  def questioning?(msg)
    msg.end_with?('?')
  end
end
