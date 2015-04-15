class Anagram
  attr_reader :word, :sorted_letters

  def initialize word
    @word = word
    @sorted_letters = sort_letters word.downcase
  end

  def match candidates
    candidates.select{|c| c if check_match c}
  end

  private

  def check_match candidate
    return false if same_word? candidate
    matches? candidate
  end

  def matches? candidate
    sorted_letters == sort_letters(candidate.downcase)
  end

  def same_word? candidate
    candidate.downcase == word.downcase
  end

  def sort_letters word
    word.split(//).sort
  end
end
