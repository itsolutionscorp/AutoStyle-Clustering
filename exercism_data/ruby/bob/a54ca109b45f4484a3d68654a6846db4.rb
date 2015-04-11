class Bob

  def hey( phrase)
    if silent?( phrase )
      "Fine. Be that way!"
    elsif yelling?( phrase )
      "Woah, chill out!"
    elsif question?( phrase )
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silent?( phrase )
    phrase.strip == ''
  end

  def yelling?( phrase )
    phrase.strip.upcase == phrase.strip
  end

  def question?( phrase )
    phrase[-1] == '?'
  end

end
