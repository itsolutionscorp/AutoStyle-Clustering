class Bob
  attr_accessor :statement
  
  def silent?
    @statement.strip.empty? ? true : false
  end
  
  def shouting?
    @statement == @statement.upcase ? true : false
  end
  
  def question?
    @statement.end_with?('?') ? true : false
  end
  
  def hey statement
    @statement = statement
    return "Fine. Be that way!" if silent?
    return "Woah, chill out!" if shouting?
    return "Sure." if question?
    return "Whatever."
  end
end
