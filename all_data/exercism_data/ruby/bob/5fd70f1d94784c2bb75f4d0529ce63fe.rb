class Bob
  def hey(message)
    case message
    when message =~ /[a-z]/i ? message.upcase : nil
      "Woah, chill out!"
    when /\?\z/
      "Sure."
    when /\A\s*\z/
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
