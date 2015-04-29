class Bob
  def hey(input)
    input.extend(Speech)
    case 
    when input.silent?
      "Fine. Be that way!"
    when input.shouted?
      "Woah, chill out!"
    when input.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

module Speech
  def silent?
    self.empty? || self =~ /^ +$/
  end
  def shouted?
    !self.silent? && self.upcase == self
  end
  def question?
    self.end_with?("?")
  end
end
