class Bob

  def hey(text)
    case text
    when /^\W*\Z/
      "Fine. Be that way!"
    when text.upcase
      'Woah, chill out!'
    when /\?\Z/
      'Sure.'
    else
      "Whatever."
    end
  end
end
