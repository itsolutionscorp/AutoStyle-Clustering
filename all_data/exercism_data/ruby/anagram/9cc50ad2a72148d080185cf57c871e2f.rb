class Anagram
  attr_reader :pivot
  private :pivot

  def initialize(word)
    @pivot = word_to_sorted_chars(word)
  end

  def match(candidates)
    candidates.select { |c| word_to_sorted_chars(c) == pivot }
  end

  private
  def word_to_sorted_chars(word)
    word.downcase.chars.sort
  end
end
