class Bob
  def hey(statement)
    if has_content?(statement)
      'Fine. Be that way.'
    elsif is_shouting?(statement)
      'Woah, chill out!'
    elsif is_question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def has_content?
    !statement || statement.empty?
  end

  def is_shouting?(statement)
    statement == statement.upcase
  end

  def is_question?(statement)
    statement.end_with('?')
  end
end
