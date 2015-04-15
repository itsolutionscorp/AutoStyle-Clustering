class Bob
  def hey(something)
    something = something.to_s.strip

    return 'Fine. Be that way.' if quiet?(something)
    return "Woah, chill out!"   if shouting?(something)
    return "Sure."              if asking?(something)
    return "Whatever."
  end

  private

  def shouting?(something)
    something.upcase == something
  end

  def asking?(something)
    something.end_with?("?")
  end

  def quiet?(something)
    something.empty?
  end
end
