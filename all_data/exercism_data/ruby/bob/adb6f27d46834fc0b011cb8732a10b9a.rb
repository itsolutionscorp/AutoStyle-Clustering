class Bob

  def hey(heard)
    if silent?(heard)
      "Fine. Be that way!"
    elsif yelling?(heard)
      "Woah, chill out!"
    elsif question?(heard)
      "Sure."
    else
      "Whatever."
    end
  end

private

  BLANK = /^\s*$/

  def silent?(heard)
    BLANK === String(heard)
  end

  def question?(heard)
    heard.end_with? "?"
  end

  def yelling?(heard)
    heard.upcase == heard
  end

end
