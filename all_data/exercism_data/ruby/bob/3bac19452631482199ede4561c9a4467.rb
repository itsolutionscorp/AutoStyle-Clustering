class Bob

  def hey(question)
    case
    when ends_with_question_mark(question) && includes_lowercase(question)
      'Sure.'
    when !ends_with_question_mark(question) && includes_lowercase(question)
      'Whatever.'
    when includes_uppercase(question) && !includes_lowercase(question)
      'Woah, chill out!'
    else
      'Fine. Be that way!'
    end
  end

  private

  def ends_with_question_mark(q)
    q =~ /[?]$/
  end

  def includes_lowercase(q)
    q =~ /[[:lower:]]/
  end

  def includes_uppercase(q)
    q =~ /[[:upper:]]+/
  end
end
