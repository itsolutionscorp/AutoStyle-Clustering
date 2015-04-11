class Bob
  def hey(msg)
    if shouting?(msg)
      "Woah, chill out!"
    elsif question?(msg)
      "Sure."
    elsif numeric?(msg)
      "Woah, chill out!"
    elsif blank?(msg)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private
  def shouting?(msg)
    msg =~ /[A-Z]{4,}/
  end

  def question?(msg)
    msg[-1] == '?'
  end

  def numeric?(msg)
    msg =~ /[0-9]{1}/
  end

  def blank?(msg)
    msg.strip.empty?
  end
end
