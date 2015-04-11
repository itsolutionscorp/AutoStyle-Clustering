class Bob
  
  def hey(statement)
    if silence?(statement)
      return 'Fine. Be that way!'
    elsif shouting?(statement)
      return 'Woah, chill out!'
    elsif question?(statement)
      return 'Sure.'
    end
    'Whatever.'
  end
  
  private
  
  def silence?(statement)
    statement.nil? || statement.strip.length == 0
  end
  
  def shouting?(statement)
    statement =~ /[A-Z]+/ && statement.upcase == statement
  end
  
  def question?(statement)
    statement[-1, 1] == '?'
  end
  
end
