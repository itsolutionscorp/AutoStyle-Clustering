class Bob
  def hey(statement)
    @statement = statement.to_s.strip

    if was_sighed_at?
      'Fine. Be that way!'
    elsif was_yelled_at?
      'Woah, chill out!'
    elsif was_questioned?
      'Sure.'
    else
      'Whatever.'
    end
  end

private

  def was_sighed_at?
    @statement.empty?
  end

  def was_questioned?
    @statement.end_with?('?')
  end

  def was_yelled_at?
    !was_sighed_at? && @statement.upcase == @statement
  end
end
