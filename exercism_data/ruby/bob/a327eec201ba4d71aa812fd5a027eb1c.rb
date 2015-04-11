class Bob
  def hey(input)
    input.strip!

    if is_silent(input)
      output = 'Fine. Be that way!'
    elsif is_shout(input)
      output = 'Woah, chill out!'
    elsif is_question(input)
      output = 'Sure.'
    else 
      output = 'Whatever.'
    end

    return output
  end

  def is_silent(input)
    return input.empty?
  end

  def is_shout(input)
    return input == input.upcase
  end

  def is_question(input)
    return input.end_with?('?')
  end
end
