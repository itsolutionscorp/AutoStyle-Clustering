class Bob

  def hey(text)
    return "Fine. Be that way." if is_silent?(text)
    return "Woah, chill out!" if is_shouting? text
    return "Sure." if is_asking?(text)

    "Whatever."
  end
  
  private

  def is_shouting?(text)
    text.upcase == text
  end

  def is_asking?(text)
     text.end_with?('?')
  end

  def is_silent?(text)
    text.nil? or text.empty?
  end

end
