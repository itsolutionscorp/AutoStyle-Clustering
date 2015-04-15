class Bob

  attr_reader :statement

  def hey(statement)
    @statement = statement
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

  def silence?
    statement == '' || statement == nil || !statement.match(/\S/)
  end

  def yelled_at?
    statement.upcase == statement
  end

  def question?
    statement.end_with?('?')
  end
end
