class Bob
  def hey(message)
    case message.to_s
    when /\A[^a-z]+\z/
      "Woah, chill out!"
    when /\?\z/
      "Sure."
    when ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
