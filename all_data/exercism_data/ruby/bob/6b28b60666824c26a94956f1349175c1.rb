class Bob
  def hey(input)
    if is_silence?(input)
      'Fine. Be that way!'
    elsif is_shouting?(input)
      'Woah, chill out!'
    elsif is_question?(input)
      'Sure.'
    else
      'Whatever.'
    end
  end
  def is_silence?(input)
    input.strip == ""
  end
  def is_question?(input)
    input.end_with?('?')
  end
  def is_shouting?(input)
    input.upcase == input
  end
end
