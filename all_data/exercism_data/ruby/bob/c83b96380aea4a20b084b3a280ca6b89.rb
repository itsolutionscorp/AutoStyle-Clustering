class Bob
  def hey(sentence)
    return "Woah, chill out!" if sentence.all_uppercase? && sentence.contains_letters?
    return "Sure." if sentence.question?
    return "Fine. Be that way!" if sentence.blank?
    "Whatever."
  end
end

class String
  def contains_letters?
    chars.any? {|c| c =~ /[a-z]/i}
  end

  def all_uppercase?
    self == self.upcase
  end

  def question?
    end_with? '?'
  end

  def blank?
    self =~ /\A\s*\z/
  end
end
