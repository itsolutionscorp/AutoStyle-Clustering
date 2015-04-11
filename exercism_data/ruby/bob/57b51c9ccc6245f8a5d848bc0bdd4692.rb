class Bob
  def hey(arg)
    return "Fine. Be that way." if silence?(arg)
    return "Woah, chill out!" if shouting?(arg)
    return "Sure." if asking?(arg)
    "Whatever."
  end

  def shouting?(arg)
    arg == arg.upcase
  end

  def asking?(arg)
    arg.end_with?("?")
  end

  def silence?(arg)
    arg ? arg.empty? : true
  end
end
