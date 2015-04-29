class Bob

  def hey(voiced)
    if mummed?(voiced)
      'Fine. Be that way!'
    elsif shouted?(voiced)
      'Woah, chill out!'
    elsif asked?(voiced)
      'Sure.'
    else
      'Whatever.'
    end
  end

private

  def shouted?(s)
    s == s.upcase
  end

  def asked?(s)
    s.end_with?('?')
  end

  def mummed?(s)
    s.to_s.strip.length == 0
  end

end
