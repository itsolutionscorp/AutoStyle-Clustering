class Bob
  def hey(message)
    message = message.to_s.strip

    if message.empty?
      "Fine. Be that way!"
    elsif message =~ /[A-Z]+/ && message !~ /[a-z]/
      "Woah, chill out!"
    elsif message.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
