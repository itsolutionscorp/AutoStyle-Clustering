class Bob

  def hey string
    return 'Fine. Be that way!' if check_for_nil_and_empty(string)
    return 'Woah, chill out!' if check_for_upcase(string)
    return 'Sure.' if check_for_ends_in_question_mark(string)
    return 'Whatever.'
  end

  private

  def check_for_nil_and_empty string
    string.to_s.strip.empty?
  end

  def check_for_upcase string
    string == string.upcase
  end

  def check_for_ends_in_question_mark string
    string.end_with?('?')
  end

end
