class Bob
  def hey(something)
    if blank?(something)
      "Fine. Be that way."
    elsif uppercase?(something)
      "Woah, chill out!"
    elsif question?(something)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def question?(something)
    something.end_with?("?")
  end

  def blank?(something)
    !something || something.length == 0
  end

  def uppercase?(something)
    something == something.upcase
  end
end
