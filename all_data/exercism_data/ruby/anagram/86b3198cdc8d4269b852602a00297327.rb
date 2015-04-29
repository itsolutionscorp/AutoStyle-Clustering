class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.each_with_object([]) do |candidate, anagrams|
      if same_letters?(candidate) && not_same_word?(candidate)
        anagrams << candidate
      end
    end
  end

  private

  def same_letters?(candidate)
    candidate.downcase.chars.sort == word.downcase.chars.sort
  end

  def not_same_word?(candidate)
    candidate.downcase != word.downcase
  end
end
