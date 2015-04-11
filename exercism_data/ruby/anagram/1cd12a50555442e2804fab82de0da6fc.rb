class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    candidates.select do |candidate|
      next if word == candidate.downcase
      next unless word.size == candidate.size

      word.chars.sort == candidate.downcase.chars.sort
    end
  end
end
