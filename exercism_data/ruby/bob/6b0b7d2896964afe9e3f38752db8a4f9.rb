class Bob
  def hey(statement)
    if not_talking?(statement)
      return "Fine. Be that way."
    elsif is_shouting?(statement)
      return "Woah, chill out!"
    elsif asking_a_question?(statement)
      return "Sure."
    else
      return "Whatever."
    end
  end

  private

  def not_talking?(statement)
    !statement || statement.empty?
  end

  def is_shouting?(statement)
    statement == statement.upcase
  end

  def asking_a_question?(statement)
    statement.end_with?("?")
  end
end
