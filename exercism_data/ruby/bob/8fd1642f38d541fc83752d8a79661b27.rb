class Bob

  # Process the statement.  Return how Bob would reply to it.
  def hey(statement)
    
    # Have to test shouting first.  Bob considers it shouting, even if it's a 
    # shouted question
    if is_shouting? statement
      'Woah, chill out!'
    elsif is_question? statement
      'Sure.'
    elsif is_silence? statement
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end


  # It's shouting if there's any letters in the statment, and none of them are 
  # lowercase
  def is_shouting?( statement )
    statement =~ /[[:alpha:]]/ and statement !~ /[[:lower:]]/
  end


  # It's a question if it ends in a question mark
  def is_question?( statement )
    statement.end_with? '?'
  end


  # It's silence if there's only whitespace
  def is_silence?( statement )
    statement =~ /\A[[:space:]]*\z/
  end

end
