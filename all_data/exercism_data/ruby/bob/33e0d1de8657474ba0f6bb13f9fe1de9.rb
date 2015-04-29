class Bob
  def hey(input)
    if is_empty?(input)
      'Fine. Be that way!'
    elsif is_shouting?(input)
      'Woah, chill out!'
    elsif is_question?(input)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def is_question?(input)
    input.chars.last == '?'
  end

  def is_empty?(input)
    input.strip == ''
  end

  def is_shouting?(input)
    input == input.upcase
  end
end
