class Bob
  def hey(greeting)
    return 'Fine. Be that way!' if silence?(greeting)
    return 'Woah, chill out!' if shouting_with_letters?(greeting)
    return 'Sure.' if question?(greeting)
    'Whatever.'
  end

  private

  def shouting?(greeting)
    greeting.upcase == greeting
  end

  def has_letters?(greeting)
    greeting =~ /[a-zA-Z]/
  end

  def shouting_with_letters?(greeting)
    shouting?(greeting) && has_letters?(greeting)
  end

  def question?(greeting)
    greeting.end_with?('?')
  end

  def silence?(greeting)
    greeting.strip.empty?
  end
end
