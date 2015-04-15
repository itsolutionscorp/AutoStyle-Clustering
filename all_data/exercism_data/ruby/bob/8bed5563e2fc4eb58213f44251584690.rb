class Bob

  def hey saying
    return "Fine. Be that way." if saying.to_s.empty?
    return "Woah, chill out!" if saying == saying.upcase
    return "Sure." if saying.end_with? "?"
    "Whatever."
  end

end
