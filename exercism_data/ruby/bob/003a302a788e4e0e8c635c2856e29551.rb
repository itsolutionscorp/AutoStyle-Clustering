class Teenager

  def self.when_told(statement)
    new(statement)
  end

  def initialize(statement)
    @statement = statement
  end

  def responds
    return 'Woah, chill out!' if _shouting?
    return 'Sure.' if _questioning?
    return 'Fine. Be that way!' if _silent?
    'Whatever.'
  end

  private

  attr_reader :statement

  def _alphabetic_characters_from(statement)
    alphanumeric_characters = statement.split('')
    alphanumeric_characters.select { |alphanumeric_character| alphanumeric_character =~ /[A-Za-z]/ }
  end

  def _all_uppercase?(characters)
    characters.join('') =~ /\A[A-Z]+\Z/
  end

  def _shouting?
    _all_uppercase?(_alphabetic_characters_from(statement))
  end

  def _questioning?
    statement.end_with?('?')
  end

  def _silent?
    statement.strip.empty?
  end
end

class Bob

  def hey(statement)
    Teenager.when_told(statement).responds
  end
end
