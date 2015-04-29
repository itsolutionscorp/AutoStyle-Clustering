class Bob
  def hey(message)
    message = message.dup.delete "\n"
    if message.upcase =~ /[A-Z]+/ && message.upcase == message
      "Woah, chill out!"
    elsif message =~ /.*\?$/
      "Sure."
    elsif message.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
