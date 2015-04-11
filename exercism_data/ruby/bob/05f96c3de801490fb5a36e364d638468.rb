class Teenager
end

class Bob < Teenager
  def hey(s)
    return "Fine. Be that way!" if s.nil? || s.empty?
    return "Woah, chill out!" if s.question? && s.capitolized?
    return "Sure." if s.question?
    return "Woah, chill out!" if s.capitolized?
    return "Whatever."
  end
end

class String
  def capitolized?
    self == self.upcase
  end
  def question?
    self[-1,1] == '?'
  end
end
