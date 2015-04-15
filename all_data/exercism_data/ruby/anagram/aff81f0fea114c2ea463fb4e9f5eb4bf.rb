class Anagram
  def initialize(word)
    @word = word.downcase
    @alphabetized_word = alphabetize(@word)
  end

  def match(candidates)
    candidates.select do |candidate|
      next if candidate.size != word.size

      alphabetize(candidate) == alphabetized_word && candidate != word
    end
  end

  private

  attr_reader :word, :alphabetized_word

  def alphabetize(s)
    s.split(//).sort!
  end
end
