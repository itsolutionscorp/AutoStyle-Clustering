class Bob
  def hey(input)
    return 'Fine. Be that way!' if empty? input
    return 'Woah, chill out!' if yelling? input
    return 'Sure.' if question? input

    'Whatever.'
  end

  private

  def empty?(input)
    input.strip.empty?
  end

  def question?(input)
    input.end_with? '?'
  end

  def yelling?(input)
    input.upcase == input
  end
end
