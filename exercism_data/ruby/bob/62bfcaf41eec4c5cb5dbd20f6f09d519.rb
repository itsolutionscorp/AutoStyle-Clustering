class Bob

  def hey(question)
    case
    when ends_with_question_mark(question) && question =~ /[[:lower:]]/
      'Sure.'
    when question =~ /[^?]$/ && question =~ /[[:lower:]]/
      'Whatever.'
    when question =~ /[[:upper:]]+/
      'Woah, chill out!'
    else
      'Fine. Be that way!'
    end
  end

  private

  def ends_with_question_mark(q)
    q =~ /[?]$/
  end

end
