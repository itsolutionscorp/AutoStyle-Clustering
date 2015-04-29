class Anagram
  attr_reader :word, :sorted_letters

  def initialize word
    @word = word
    @sorted_letters = sort_letters word.downcase
  end

  def match candidates
    candidates.select{|candidate| matches? candidate}
  end

  private

  def matches? candidate
    return false if same_word? candidate
    sorted_letters == sort_letters(candidate.downcase)
  end

  def same_word? candidate
    candidate.downcase == word.downcase
  end

  def sort_letters word
    word.chars.sort
  end
end
