class Bob
  def hey(input)
    input.strip!

    if is_silent(input)
      'Fine. Be that way!'
    elsif is_shout(input)
      'Woah, chill out!'
    elsif is_question(input)
      'Sure.'
    else 
      'Whatever.'
    end
  end

  def is_silent(input)
    input.empty?
  end

  def is_shout(input)
    input == input.upcase
  end

  def is_question(input)
    input.end_with?('?')
  end
end
