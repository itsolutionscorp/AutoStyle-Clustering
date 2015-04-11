class Bob

  def hey(text)
    if text =~ /^\W*$/
      "Fine. Be that way!"
    elsif text.upcase == text
      'Woah, chill out!'
    elsif text =~ /\?$/
      'Sure.'
    else
      "Whatever."
    end
  end
end
