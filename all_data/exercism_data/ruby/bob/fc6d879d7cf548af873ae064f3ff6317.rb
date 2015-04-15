# Your basic semicommunicative teenager
class Bob
  def hey(message)
    case message
    when /^[^a-z]*[A-Z][^a-z]*$/
      "Woah, chill out!"
    when /\?\z/m
      "Sure."
    when /\A\s*\Z/m
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
