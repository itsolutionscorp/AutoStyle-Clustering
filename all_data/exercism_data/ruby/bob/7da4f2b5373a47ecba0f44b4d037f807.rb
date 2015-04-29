class Bob
  def hey(message)
    if all_caps? message
      "Woah, chill out!"
    elsif question? message
      "Sure."
    elsif silent? message
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  protected
  def all_caps?(message)
    has_capital_letter?(message) && message == message.upcase
  end

  def has_capital_letter?(message)
    message =~ /[[:upper:]]+/
  end

  def question?(message)
    message.end_with? "?"
  end

  def silent?(message)
    message && message.strip.length == 0
  end
end
