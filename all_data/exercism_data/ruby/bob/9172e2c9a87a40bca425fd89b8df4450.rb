class Bob
  def hey(statement)
    if silent?(statement)
      'Fine. Be that way!'
    elsif shouted?(statement)
      'Woah, chill out!'
    elsif question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def shouted?(statement)
    statement.upcase == statement
  end

  def question?(statement)
    statement[-1] == '?'
  end

  def silent?(statement)
    statement.nil? || statement.strip.empty?
  end
end
