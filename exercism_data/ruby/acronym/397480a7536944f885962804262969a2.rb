class Acronym
  def self.abbreviate(phrase)
    Acronym.new(phrase).abbreviation
  end

  def abbreviation
    initials.join
  end

  private

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def initials
    component_words.map { |w| w.chars.first.upcase }
  end

  def component_words
    words.map(&method(:decompose_word)).flatten
  end

  def words
    phrase.split(' ')
  end

  def decompose_word(word)
    decomposed = word.scan(/[A-Z][a-z]+/)
    decomposed.empty? ? word.split('-') : decomposed
  end
end
