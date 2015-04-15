class Bob
  def hey(statement)
    @statement = statement.to_s

    if sighed_at?
      'Fine. Be that way.'
    elsif questioned?
      'Sure.'
    elsif yelled_at?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  def sighed_at?
    @statement.empty?
  end

  def questioned?
    @statement.end_with?('?')
  end

  def yelled_at?
    @statement.upcase == statement
  end
end
