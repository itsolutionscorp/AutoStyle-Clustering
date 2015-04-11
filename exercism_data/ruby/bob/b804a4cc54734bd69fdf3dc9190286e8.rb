class Bob

  def hey(text)
    return "Fine. Be that way." if silent?(text)
    return "Woah, chill out!" if shouting?(text)
    return "Sure." if asking?(text)

    return "Whatever."
  end
  
  private

  def shouting?(text)
    text.upcase == text
  end

  def asking?(text)
     text.end_with?('?')
  end

  def silent?(text)
    text.nil? or text.empty?
  end

end
