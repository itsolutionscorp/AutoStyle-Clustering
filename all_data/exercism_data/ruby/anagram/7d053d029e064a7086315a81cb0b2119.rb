class Anagram
  attr_reader :word

  def initialize(word)
    @word = normalize word
  end

  def match(candidates)
    candidates.select do |c|
      anagram? normalize(c) and normalize(c) != word
    end
  end

  private

  def normalize(input)
    input.downcase
  end

  def permutations
    word.chars.permutation.collect { |p| p.join '' } 
  end

  def anagram?(candidate)
    permutations.include? candidate
  end
end
