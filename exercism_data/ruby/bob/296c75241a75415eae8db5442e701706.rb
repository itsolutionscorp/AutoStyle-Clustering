class Bob

  def hey(input)
    if silence?(input)
      'Fine. Be that way!'
    elsif yelling?(input)
      'Woah, chill out!'
    elsif question?(input)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def question?(input)
    input.end_with? '?'
  end

  def yelling?(input)
    input.upcase == input && input =~ /[A-Z]/
  end

  def silence?(input)
    input =~ /\A\s*\z/
  end
end
