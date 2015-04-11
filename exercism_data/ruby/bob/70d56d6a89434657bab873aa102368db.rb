class Bob
  def hey(input)
    input = input.strip
    if nil_or_empty(input)
      'Fine. Be that way!'
    elsif input_shouting(input)
      'Woah, chill out!'
    elsif input_ends_with_question_mark(input)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def nil_or_empty(input)
    input.nil? || input.length == 0
  end

  def input_shouting(input)
    input == input.upcase
  end

  def input_ends_with_question_mark(input)
    input.end_with?("?")
  end

end
