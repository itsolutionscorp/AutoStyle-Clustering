class Bob

  def hey(input)
    if silence?(input)
      'Fine. Be that way!'
    elsif yell?(input)
       'Woah, chill out!'
    elsif question?(input)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def question?(input)
    input.end_with?('?')
  end

  def yell?(input)
    input == input.upcase
  end

  def silence?(input)
    input.to_s.strip.empty? 
  end
end
