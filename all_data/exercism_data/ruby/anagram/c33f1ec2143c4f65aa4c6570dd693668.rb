class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(list_of_possibles)
    matches = []
    list_of_possibles.each do |possible|
      matches << possible if is_a_match?(possible)
    end
    matches
  end

  private

  def is_a_match?(possible_match)
    return false if possible_match.downcase == word.downcase
    return true  if splat(possible_match) == splat(word)
  end

  def splat(a_word)
    a_word.downcase.split(//).sort
  end
end
