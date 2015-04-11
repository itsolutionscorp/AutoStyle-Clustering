class Bob
  def hey(speech)
    if shouting(speech)
      "Woah, chill out!"
    elsif silence(speech)
      "Fine. Be that way!"
    elsif question(speech)
      "Sure."
    else
      "Whatever."
    end
  end

  def shouting(speech)
    speech.match(/[A-Z]/) && !speech.match(/[a-z]/)
  end

  def silence(speech)
    speech.strip.empty?
  end

  def question(speech)
    speech[-1] == '?'
  end
end
