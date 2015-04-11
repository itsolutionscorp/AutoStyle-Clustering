class Bob

  def hey(input)
    return 'Fine. Be that way!' if silence?(input)
    return 'Woah, chill out!' if yell?(input)
    return 'Sure.' if question?(input)
    'Whatever.'
  end

  def question?(input)
    input =~ /\?\z/
  end

  def yell?(input)
    input.upcase == input
  end

  def silence?(input)
    input.to_s == ''
  end

end
