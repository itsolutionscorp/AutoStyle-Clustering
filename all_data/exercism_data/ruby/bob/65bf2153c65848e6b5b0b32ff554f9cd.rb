class Bob
  def hey(input)
    speech = Speech.new(input)
    case 
    when speech.is_silent
      "Fine. Be that way!"
    when speech.is_shouted
      "Woah, chill out!"
    when speech.is_a_question
      "Sure."
    else
      "Whatever."
    end
  end
end

class Speech < String
  def is_silent
    self.empty? || self =~ /^ +$/
  end
  def is_shouted
    self.upcase == self
  end
  def is_a_question
    self.last == "?"
  end
end

class String
  def last
    self[-1]
  end
end
