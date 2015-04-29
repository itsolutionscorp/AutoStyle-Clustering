class Bob

  def hey(text)
    case text
    when empty?
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
