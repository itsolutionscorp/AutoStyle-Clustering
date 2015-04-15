class Acronym
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def self.abbreviate(phrase)
    new(phrase).abbreviate
  end

  def abbreviate
    phrase.gsub!(/[,:-]/, " ")

    if recursive_abbreviation?(phrase)
      phrase.split.first
    else
      make_abbreviation(phrase)
    end
  end


  def make_abbreviation(phrase)
    phrase.split.map do |word|
      word.capitalize! unless upper_case?(word.chars.first)
      word.scan(/[A-Z]/)
    end.join
  end

  def upper_case?(character)
    character == character.upcase
  end

  def recursive_abbreviation?(word)
    upper_case?(word[0]) && upper_case?(word[1])
  end
end
