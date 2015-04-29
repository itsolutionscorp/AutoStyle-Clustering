class Bob
  def hey(msg)
    case msg
    when nil, ''
      "Fine. Be that way."
    when /\A[^a-z]+\Z/
      "Woah, chill out!"
    when /\?$/
      "Sure."
    else
      "Whatever."
    end
  end
end
