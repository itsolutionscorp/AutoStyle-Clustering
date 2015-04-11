class Bob

  def hey( statement )
    
    # Have to test shouting first.  Bob considers it shouting, even if it's a 
    # shouted question
    if shouting? statement
      'Woah, chill out!'
    elsif question? statement
      'Sure.'
    elsif silence? statement
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end


private
  def shouting?( statement )
    statement =~ /[[:alpha:]]/ and statement !~ /[[:lower:]]/
  end


  def question?( statement )
    statement.end_with? '?'
  end


  def silence?( statement )
    statement =~ /\A[[:space:]]*\z/
  end

end
