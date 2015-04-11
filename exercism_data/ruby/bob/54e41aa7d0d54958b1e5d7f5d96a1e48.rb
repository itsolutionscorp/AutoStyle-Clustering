class Bob

  def hey string
    if check_for_nil_and_empty
    elsif check_for_upcase
    elsif check_for_ends_in_question_mark
    else check_for_string
    end
  end

  private

  def check_for_nil_and_empty
    if string.nil? or string.strip.empty?
      'Fine. Be that way!'
    end
  end

  def check_for_upcase
    if string == string.upcase
      'Woah, chill out!'
    end
  end

  def check_for_ends_in_question_mark
    if string.end_with?('?')
      'Sure.'
    end
  end

  def check_for_string
    'Whatever.'
  end

end
