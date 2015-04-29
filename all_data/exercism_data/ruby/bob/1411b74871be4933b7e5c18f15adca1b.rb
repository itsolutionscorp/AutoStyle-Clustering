class Bob
  def hey(message)
    case message
    when /\A[^a-z]+\Z/
      "Woah, chill out!"
    when /\?\Z/
      "Sure."
    when "", nil
      "Fine. Be that way."
    else
      "Whatever."
    end
  end
end
