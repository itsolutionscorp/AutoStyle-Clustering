class Bob

def hey(sentence)
    sentence.gsub!(/\n/,' ')
    case
    when statement_is_forceful(sentence) then 'Woah, chill out!'
    when statement_is_a_question(sentence) then 'Sure.'
    when statement_is_empty(sentence) then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  private
  def statement_is_forceful(statement)
    statement.match(/^[A-Z\s]*[0-9\W]*[A-Z]+[0-9A-Z\?!\s]*$/)
  end

  def statement_is_a_question(statement)
    statement.match(/^(([A-Z])|\d*)[a-zA-Z0-9 ,\.!]*\?$/)
  end

  def statement_is_empty(statement)
    statement.match(/^\W*$/)
  end
end
