class Bob

  def hey(words)
    
    phrase = Phrase.new(words)

    if phrase.silent?
      "Fine. Be that way!"
    elsif phrase.yelled?
      "Woah, chill out!"
    elsif phrase.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Phrase < String

  def question?
    self.end_with?('?')
  end

  def silent?
    self.strip.empty?
  end

  def yelled?
    self.count("a-zA-Z") > 0 && self.upcase == self
  end

end
