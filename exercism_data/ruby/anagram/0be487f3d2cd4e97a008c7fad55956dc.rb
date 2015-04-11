class Anagram
  attr_accessor :word

  def initialize word
    @word = word
  end

  def match candidates
    candidates.select do |candidate|
      chars_sort(word) == chars_sort(candidate) unless equal_word?(candidate)
    end
  end

  private

  def chars_sort word
    word.downcase.chars.sort
  end

  def equal_word? candidate
    candidate.downcase == word.downcase
  end

end
