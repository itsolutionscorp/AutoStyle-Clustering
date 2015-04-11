class Bob
  def hey(sentence)
    return "Woah, chill out!" if sentence.uppercase? && sentence.contains_letters?
    return "Sure." if sentence.question?
    return "Fine. Be that way!" if sentence.blank?
    "Whatever."
  end
end

class String
  def contains_letters?
    self =~ /[a-z]/i
  end

  def uppercase?
    self == self.upcase
  end

  def question?
    end_with? '?'
  end

  def blank?
    strip.empty?
  end
end
