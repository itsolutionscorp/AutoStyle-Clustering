class Bob
  def hey(message)
    if message.nil? || message.empty?
      "Fine. Be that way!"
    elsif message =~ /(^[A-Z\d][\dA-Z\s\W]+)/
      "Woah, chill out!"
    elsif message.end_with? "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
