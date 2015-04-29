class Bob

  def hey(input)
    if person_is_silent(input)
      'Fine. Be that way.'
    elsif person_is_shouting(input)
      'Woah, chill out!'
    elsif person_is_asking_question(input)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def person_is_shouting(input)
    input == input.upcase
  end

  def person_is_asking_question(input)
    input.end_with? '?'
  end

  def person_is_silent(input)
    input.empty?
  end

end
