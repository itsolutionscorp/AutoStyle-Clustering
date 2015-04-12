class Bob
  def hey lecture
    return "Fine. Be that way!" if silence?(lecture)
    return "Woah, chill out!" if shouting?(lecture)
    return "Sure." if questioning?(lecture)
    return boring(lecture)
  end

  def silence? lecture
    lecture == nil or lecture.strip == ""
  end

  def shouting? lecture
    lecture.upcase == lecture
  end

  def questioning? lecture
    lecture.end_with?("?")
  end

  def boring lecture
    "Whatever."
  end
end