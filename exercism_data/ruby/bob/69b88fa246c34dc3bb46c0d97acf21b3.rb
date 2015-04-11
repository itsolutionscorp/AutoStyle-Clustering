class Bob
  def hey input
    return 'Fine. Be that way!' if is_quietly_addressing?(input)
    return 'Woah, chill out!' if is_shouting?(input)
    return 'Sure.' if is_questioning?(input)
    'Whatever.'
  end

  def has_only_upper_case_letters?(input)
    input == input.upcase
  end
  alias_method :is_shouting?, :has_only_upper_case_letters?

  def is_questioning?(input)
    input.end_with?('?')
  end

  def is_quietly_addressing?(input)
    input.nil? || input.empty? || has_only_whitespace?(input)
  end

  def has_only_whitespace?(input)
    input =~ /^\s+$/
  end
end
