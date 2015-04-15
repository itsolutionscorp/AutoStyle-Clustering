class Statement

  def initialize(text)
    @text = text
  end

  def shouting?
    _all_uppercase?(_alphabetic_characters_from)
  end

  def questioning?
    text.end_with?('?')
  end

  def silent?
    text.strip.empty?
  end

  private

  attr_reader :text

  def _alphabetic_characters_from
    alphanumeric_characters = text.split('')
    alphanumeric_characters.select { |alphanumeric_character| alphanumeric_character =~ /[A-Za-z]/ }
  end

  def _all_uppercase?(characters)
    characters.join('') =~ /\A[A-Z]+\Z/
  end
end

class Bob

  def hey(text)
    statement = Statement.new(text)
    return 'Woah, chill out!' if statement.shouting?
    return 'Sure.' if statement.questioning?
    return 'Fine. Be that way!' if statement.silent?
    'Whatever.'
  end
end
