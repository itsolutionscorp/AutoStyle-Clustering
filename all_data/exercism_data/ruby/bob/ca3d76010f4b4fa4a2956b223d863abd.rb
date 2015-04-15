class Bob

  def hey(interlocution)
    string = sanitize(interlocution)
    case
    when string.upcase == string && string.match(/[A-Z]/)
      "Woah, chill out!"
    when string.match(/(.)+\?$/)
      "Sure."
    when string == ""
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end

  def sanitize(string)
    string.gsub("\n", " ").strip
  end
end
