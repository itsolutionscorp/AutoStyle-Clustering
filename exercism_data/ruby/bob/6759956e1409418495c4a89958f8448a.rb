class Bob

  YELL = /[A-Z][^a-z]/
  QUESTION = /\?\z/

  def hey(text)
    if text.strip.empty?
      "Fine. Be that way!"
    elsif text.match(YELL) && text == text.upcase
      "Whoa, chill out!"
    elsif text.match(QUESTION)
      "Sure."
    else
      "Whatever."
    end
  end

end
