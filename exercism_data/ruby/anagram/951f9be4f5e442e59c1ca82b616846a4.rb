class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    candidates.select do |candidate|
      word != candidate.downcase &&
        word_distribution == distribution_for(candidate)
    end
  end

  private

  def word_distribution
    @word_distribution ||= distribution_for(word)
  end

  def distribution_for(word)
    word.downcase.chars.each_with_object(Hash.new(0)) do |c, result|
      result[c] += 1
    end
  end
end
