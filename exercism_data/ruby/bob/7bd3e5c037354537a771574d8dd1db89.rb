class Bob

  def hey(input)
    if input == ''
      no_input_response
    elsif input == input.upcase
      shouting_response
    elsif input.end_with?('?')
      question_response
    else 
      regular_statement_response
    end
  end

  def no_input_response
    'Fine, be that way.'
  end

  def shouting_response
    'Woah, chill out!'
  end

  def question_response
    'Sure.'
  end

  def regular_statement_response
    'Whatever.'
  end
end
