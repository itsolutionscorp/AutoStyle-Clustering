class Bob
  def hey(input)
    return 'Fine. Be that way!' if is_blank?(input)
    return 'Woah, chill out!' if is_yelling?(input)
    return 'Sure.' if is_question?(input)

    'Whatever.'
  end

  def is_blank?(input)
    input.strip.empty?
  end

  def is_question?(input)
    input.end_with? '?'
  end

  def is_yelling?(input)
    input.upcase == input
  end
end
