class Bob
  def hey(statement)
    return 'Fine. Be that way!' if silence? statement
    return 'Woah, chill out!' if yelling? statement
    return 'Sure.' if making_a_question? statement
    'Whatever.'
  end

  private
  def yelling?(statement)
    statement==statement.upcase
  end

  def making_a_question?(statement)
    statement[-1]=='?'
  end

  def silence?(statement)
    statement.strip.empty?
  end
 
end
