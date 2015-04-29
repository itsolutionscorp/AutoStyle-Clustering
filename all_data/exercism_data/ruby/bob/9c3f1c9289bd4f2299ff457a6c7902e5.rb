class Bob
  def hey(message)
    if message.strip.empty?
      "Fine. Be that way!"
    elsif message[/[a-z]/i] && message == message.upcase
      "Whoa, chill out!"
    elsif message.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
