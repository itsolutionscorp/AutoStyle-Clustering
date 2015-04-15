class Bob

  def initialize

  end

  def hey(word)
    if word == ""
      "Fine. Be that way."
    elsif word.include?("?")
      "Sure."
    elsif word == word.upcase
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

end
