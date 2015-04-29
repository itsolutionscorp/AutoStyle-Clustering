class Bob < String
  def hey(message)
    if message.nil? || message.strip.empty?
      "Fine. Be that way!"
    elsif message =~ /[A-Z]/ && message.upcase == message 
      "Woah, chill out!"
    elsif message.end_with?("?")
      "Sure."
    else 
      "Whatever."
    end
  end
end
