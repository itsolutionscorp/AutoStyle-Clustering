class Bob

  def hey(spoken)
    return "Fine. Be that way!" if spoken.strip.empty?
    return "Whoa, chill out!" if upper?(spoken)
    return "Sure." if question?(spoken)
    return "Whatever."
  end

  def upper?(str)
    (str === str.upcase) && (str.scan(/[A-Za-z]/).count > 0)
  end

  def question?(str)
    str[-1] == '?'
  end

end
