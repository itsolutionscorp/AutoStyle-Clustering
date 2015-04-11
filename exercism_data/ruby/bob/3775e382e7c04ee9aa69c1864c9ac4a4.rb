class Bob

  def hey message
    return 'Woah, chill out!' if all_caps? message

    case message
    when ends_with_question_mark  then 'Sure.'
    when only_contains_whitespace then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  private
  def all_caps? message
    has_uppercase = message =~ /[[:upper:]]/
    has_lowercase = message =~ /[[:lower:]]/
    has_uppercase && !has_lowercase
  end

  def only_contains_whitespace
    /\A[[:space:]]*\z/
  end

  def ends_with_question_mark
    /\?\z/
  end

end
