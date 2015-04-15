class Bob
  def hey(something)
    if silence?(something)
      "Fine. Be that way!"
    elsif yelling?(something)
      "Woah, chill out!"
    elsif question?(something)
      "Sure."
    else 
      "Whatever."
    end
  end

  private

  def silence?(something)
    something.strip.empty?
  end

  def question?(something)
    something.strip.end_with?("?")
  end

  def yelling?(something)
    something.strip.upcase == something
  end
end
