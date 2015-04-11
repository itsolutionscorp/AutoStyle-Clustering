class Bob

  def hey(speech)
    if yelling?(speech)
      'Woah, chill out!'
    elsif question?(speech)
      'Sure.'
    elsif silence?(speech)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def question?(speech)
    speech[-1] == "?"
  end

  def yelling?(speech)
    speech == speech.upcase && speech.match(/[A-Za-z]+/)
  end

  def silence?(speech)
    speech.strip.empty?
  end

end
