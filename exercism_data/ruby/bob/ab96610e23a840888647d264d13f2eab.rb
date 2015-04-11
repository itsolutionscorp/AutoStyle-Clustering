class Bob
  def hey(arg)
    return "Woah, chill out!" if shouting?(arg)
    return "Sure." if asking?(arg)
    return "Fine. Be that way." if silence?(arg)
    "Whatever."
  end

  def shouting?(arg)
    /[A-Z\s]*[1-9,\W\s]*[A-Z\s]+[1!]*$/ === arg
  end

  def asking?(arg)
    /[A-Z][a-z\s]+[?]$/ === arg
  end

  def silence?(arg)
    arg ? arg.empty? : true
  end
end
