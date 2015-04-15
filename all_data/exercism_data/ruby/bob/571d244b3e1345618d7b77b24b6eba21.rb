class Bob
  def hey(statement)
    @statement = statement
    return 'Fine. Be that way!' if silence?
    return 'Woah, chill out!' if shouting?
    return 'Sure.' if question?
    return "Whatever."
  end

  private

  def shouting?
    @statement.upcase == @statement
  end
  
  def question?
    @statement.end_with? '?'
  end

  def silence?
    @statement.to_s.strip == ''
  end

end
