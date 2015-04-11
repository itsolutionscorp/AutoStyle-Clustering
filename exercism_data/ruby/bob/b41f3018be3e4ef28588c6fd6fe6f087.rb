class Bob
  
  def hey(statement)
    return 'Fine. Be that way!' if silence?(statement)
    return 'Woah, chill out!'   if shouting?(statement)
    return 'Sure.'              if question?(statement)
    'Whatever.'
  end
  
  private
  
  def silence?(statement)
    statement.strip.empty?
  end
  
  def shouting?(statement)
    statement =~ /[A-Z]+/ && statement.upcase == statement
  end
  
  def question?(statement)
    statement.end_with? '?'
  end
  
end
