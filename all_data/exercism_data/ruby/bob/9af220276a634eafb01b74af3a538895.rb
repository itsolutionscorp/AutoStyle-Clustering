class Bob
  def hey(message)
    case message.to_s
    when ""
      "Fine. Be that way!"
    when /\.$/, /gym/
      "Whatever."
    when /!$/, /^[^a-z]*$/
      "Woah, chill out!"
    when /\?$/
      "Sure."
    end
  end
end
