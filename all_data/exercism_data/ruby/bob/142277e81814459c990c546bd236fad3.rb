class Bob
  def hey string
    if keeps_silent?(string)
      accept
    elsif shouts?(string)
      cool_down
    elsif is_question?(string)
      agree
    else
      indifferent
    end
  end

  def indifferent
    "Whatever."
  end

  def agree
    "Sure."
  end

  def cool_down
    "Woah, chill out!"
  end

  def accept
    "Fine. Be that way!"
  end

  def shouts?(string)
    string == string.upcase
  end

  def keeps_silent?(string)
    string.strip.empty?
  end

  def is_question?(string)
    string.split('').last == "?"
  end

end
