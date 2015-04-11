class String
  def silence?
    self.strip.empty?
  end

  def has_words?
    self.match(/[a-zA-Z]/)
  end

  def question?
    self.end_with? "?"
  end

  def shouting?
    self.has_words? && self.upcase == self
  end
end


class Bob

  def hey(sentence)
    if sentence.silence?
      "Fine. Be that way!"
    elsif sentence.shouting?
      "Woah, chill out!"
    elsif sentence.question?
      "Sure."
    else
      "Whatever."
    end
  end

end
