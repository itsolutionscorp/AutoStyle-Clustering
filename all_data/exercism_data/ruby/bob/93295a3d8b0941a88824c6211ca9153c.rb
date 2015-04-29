class Bob
  def hey(arg)
    return "Fine. Be that way." if silence?(arg)
    return "Woah, chill out!" if shouting?(arg)
    return "Sure." if asking?(arg)
    "Whatever."
  end

  def shouting?(arg)
    /[A-Z\s]*[1-9,\W\s]*[A-Z\s]+[1!]*$/ === arg
  end

  def asking?(arg)
    arg.end_with?("?")
  end

  def silence?(arg)
    arg ? arg.empty? : true
  end
end
