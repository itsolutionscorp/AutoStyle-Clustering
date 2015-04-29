class Bob
  def hey(statement)
    statement = Statement.new(statement.nil? ? "" : statement)
    
    case
      when statement.silent?
        'Fine. Be that way.'
      when statement.shouting? 
        "Woah, chill out!"
      when statement.question? 
        "Sure."
      else
        "Whatever."
    end
  end
end
  
class Statement < String
  def silent?
    self == '' || self.nil?
  end
  
  def shouting?
    self =~ /^[^a-z]*$/
  end
  
  def question?
    self =~ /.*\?$/
  end
end
