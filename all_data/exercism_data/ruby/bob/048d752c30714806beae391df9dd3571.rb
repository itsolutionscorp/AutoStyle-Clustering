class Bob

  def hey statement = nil
    return "Whoa, chill out!" if statement.upcase == statement && statement =~ /[A-Z]/
    return "Sure." if statement.end_with?("?")
    return "Fine. Be that way!" if statement.strip.empty?
    return "Whatever."
  end

end
