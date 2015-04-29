class Bob

  QUESTION_REGEX = /\?\z/
  CONTAINS_CHARS_REGEX = /[A-Z]/

  def hey(str)
    if is_yelling? str
      'Woah, chill out!'
    elsif is_a_question? str
      'Sure.'
    elsif is_silence? str
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end


  private

  def is_a_question?(str)
    str =~ QUESTION_REGEX
  end


  def is_yelling?(str)
    str =~ CONTAINS_CHARS_REGEX && str.upcase == str
  end


  def is_silence?(str)
    str.strip.empty?
  end

end
