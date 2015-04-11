class Bob

  def hey(text)
    if saying_nothing?(text)
      "Fine. Be that way."
    elsif yelling?(text)
      "Woah, chill out!"
    elsif question?(text) 
      "Sure."
    else
      "Whatever."
    end
  end

  def question?(text)
    text.strip.end_with?("?")
  end

  def yelling?(text)
    text.eql?(text.upcase)
  end

  def saying_nothing?(text)
    not text or text.strip.empty?
  end

end
