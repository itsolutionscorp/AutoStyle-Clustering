class Bob
  def hey(statement)
    case
      when statement.not_talking? then "Fine. Be that way."
      when statement.shouting? then "Woah, chill out!"
      when statement.asking? then "Sure."
      else "Whatever."
    end
  end
end

class String
  def not_talking?
    self.empty?
  end

  def shouting?
    self == self.upcase
  end

  def asking?
    self.end_with?("?")
  end
end

class NilClass
  def not_talking?
    self.to_s.empty?
  end
end
