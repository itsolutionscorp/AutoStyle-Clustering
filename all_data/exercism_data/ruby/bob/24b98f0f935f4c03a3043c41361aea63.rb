class Bob
  def hey(arg)
    return "Whatever." if stating?(arg)
    return "Woah, chill out!" if shouting?(arg)
    return "Sure." if asking?(arg)
    return "Fine. Be that way." if silence?(arg)
  end

  def stating?(arg)
    /^[A-Z][a-z\s-]+['?,][a-z\s-]+[.!]$/ === arg
  end

  def shouting?(arg)
    /[A-Z\s]*[1-9,\W\s]*[A-Z\s]+[1!]*$/ === arg
  end

  def asking?(arg)
    /[A-Z][a-z\s]+[?]$/ === arg
  end

  def silence?(arg)
    arg ? /[\s]?/ === arg : true
  end
end
