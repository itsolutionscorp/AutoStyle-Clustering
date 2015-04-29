class Bob
  def hey what
    return 'Fine. Be that way!' if silence? what
    return 'Woah, chill out!' if shouting? what
    return 'Sure.' if questioning? what
    'Whatever.'
  end


private
  def silence? expression
    expression.nil? || expression.empty?
  end

  def shouting? expression
    expression.split.all? { |word| word.match /^[A-Z]*([0-9]*[,!?%^*@#$(*^]*)*$/ }
  end

  def questioning? expression
    expression.end_with? "?"
  end

end
