class Bob

  def hey(statement)
    if you_yell(statement)
      'Woah, chill out!'
    elsif you_ask_a_question(statement)
      'Sure.'
    elsif you_wait_in_silence(statement)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def you_yell(statement)
    statement =~ /[A-z]/ && statement.upcase == statement
  end

  def you_ask_a_question(statement)
    statement.end_with?('?')
  end

  def you_wait_in_silence(statement)
    statement.strip.empty?
  end
end
