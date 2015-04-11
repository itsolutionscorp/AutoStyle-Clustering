class Anagram
  attr_reader :ordinals

  def initialize(word)
    @ordinals = sorted_ordinals word.to_s.downcase
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end
  
  def anagram?(word)
    ordinals == sorted_ordinals(word.downcase)
  end

  private
  def sorted_ordinals(letters)
    letters.codepoints.sort
  end
end
