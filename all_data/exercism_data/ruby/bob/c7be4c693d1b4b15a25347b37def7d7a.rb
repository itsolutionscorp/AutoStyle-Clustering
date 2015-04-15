class Bob

  def hey(statement)
    return 'Woah, chill out!' if _shouting?(statement)
    return 'Sure.' if _questioning?(statement)
    return 'Fine. Be that way!' if _silent?(statement)
    'Whatever.'
  end

  private

  def _alphabetic_characters_from(statement)
    alphanumeric_characters = statement.split('')
    alphanumeric_characters.select { |alphanumeric_character| alphanumeric_character =~ /[A-Za-z]/ }
  end

  def _all_uppercase?(characters)
    characters.join('') =~ /\A[A-Z]+\Z/
  end
  
  def _shouting?(statement)
    _all_uppercase?(_alphabetic_characters_from(statement))
  end

  def _questioning?(statement)
    statement.end_with?('?')
  end

  def _silent?(statement)
    statement.strip.empty?
  end
end
