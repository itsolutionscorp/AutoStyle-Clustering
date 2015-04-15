class Bob
  def hey(statement)
    s = statement.extend(Expressive)
    response = "Whatever."
    response = "Fine. Be that way." if s.blank?
    response = "Woah, chill out!" if s.shouting?
    response = "Sure." if s.question?
    return response
  end
end

private

module Expressive
  def blank?
    self.nil? ? true : self.empty?
  end

  def shouting?
    self.upcase == self unless self.blank?
  end
  
  def question?
    self.end_with?('?') unless self.blank?
  end
end
