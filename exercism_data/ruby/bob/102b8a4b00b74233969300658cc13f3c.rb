class Bob
  def hey(sentence)
    return "Fine. Be that way!" if silent?(sentence)

    case cleanup(sentence)
    when shouting?
      "Woah, chill out!"
    when question?
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def cleanup(sentence)
    sentence.gsub(/[^[:alpha:]?]+/i, '') # Just leave letters and ?
  end

  def question?
    /\?$/
  end

  def shouting?
    /^[[:upper:]]+(\?)?$/
  end

  def silent?(sentence)
    sentence.strip.empty?
  end
end
