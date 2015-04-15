class Bob

  def hey(text)
    if !text or text == ''
      "Fine. Be that way."
    elsif text[-1] == '?'
      "Sure."
    elsif text.upcase == text
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end
