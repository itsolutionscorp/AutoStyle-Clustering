class Teenager
end

class Bob < Teenager
  def hey(s)
    case
    when s.nil? || s.empty?
      "Fine. Be that way!"
    when s.end_with?('?') && s.capitolized?
      "Woah, chill out!"
    when s.end_with?('?')
      "Sure."
    when s.capitolized?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end

class String
  def capitolized?
    self == self.upcase
  end
end
