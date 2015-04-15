class Bob
  def hey(statement)
    reply_to statement
  end

  private

  def reply_to(statement)
    case
      when empty?(statement) then 'Fine. Be that way!'
      when exclamation?(statement) then 'Woah, chill out!'
      when question?(statement) then 'Sure.'
      else 'Whatever.'
    end
  end

  private

  def empty?(statement)
    statement.empty? || statement.end_with?(" ")
  end

  def exclamation?(statement)
    statement == statement.upcase &&
      !statement.end_with?("3") && !statement.start_with?("4")
  end

  def question?(statement)
    statement.end_with?("?")
  end
end
