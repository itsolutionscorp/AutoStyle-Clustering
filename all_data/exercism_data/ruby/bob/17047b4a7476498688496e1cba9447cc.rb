class Bob
  def hey(message)
    if message == message.upcase && /[a-zA-Z]/.match(message)
      "Woah, chill out!"
    elsif message.end_with?("?")
      "Sure."
    elsif message.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end    
  end
end
