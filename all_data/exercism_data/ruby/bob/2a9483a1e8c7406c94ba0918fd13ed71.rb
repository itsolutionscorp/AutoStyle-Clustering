class Bob
  def hey(statement)
    s = statement.extend(Expressive)
    
    case
    when s.silent?
      response = "Fine. Be that way."
    when s.shouting?
      response = "Woah, chill out!"
    when s.question?
      response = "Sure." if s.question?
    else
      response = "Whatever."
    end
    
    return response
  end
end

private

module Expressive
  def silent?
    self.nil? ? true : self.empty?
  end

  def shouting?
    self.upcase == self unless self.silent?
  end
  
  def question?
    self.end_with?('?') unless self.silent?
  end
end
