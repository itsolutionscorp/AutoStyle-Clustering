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

  def shouted?(spoken)
    spoken == spoken.upcase
  end

  def asked?(spoken)
    spoken.end_with?('?')
  end

  def mummed?(spoken)
    spoken.to_s.strip.length == 0
  end

end
