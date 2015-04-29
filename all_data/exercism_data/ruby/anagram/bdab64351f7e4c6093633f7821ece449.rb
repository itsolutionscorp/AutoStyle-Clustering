class Anagram
  attr_reader :word, :sorted_letters

  def initialize word
    @word = word
    @sorted_letters = sort_letters word.downcase
  end

  def match candidates
    candidates.each_with_object([]) do | c, anagrams |
      next if different_length?(c) or same_word?(c)
      anagrams << c if matches?(c)
    end
  end

  private

  def matches? candidate
    sorted_letters == sort_letters(candidate.downcase)
  end

  def different_length? candidate
    candidate.length != word.length
  end

  def same_word? candidate
    candidate.downcase == word.downcase
  end

  def sort_letters word
    word.split(//).sort
  end
end
