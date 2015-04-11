class Bob

  def hey(statement)
    @statement = statement.to_s.strip
    if silence?
      'Fine. Be that way!'
    elsif yelled_at?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  attr_reader :statement

  def silence?
    statement.empty?
  end

  def yelled_at?
    statement.upcase == statement
  end

  def question?
    statement.end_with?('?')
  end
end
