class Anagram
  attr_reader :body

  def initialize(body)
    @body = body.downcase
  end

  def match(words)
    words.select{|w| anagram?(w)}
  end

  def anagram?(comparison)
    comparison = comparison.downcase
    comparison_matches?(comparison) 
  end

  private

  def dups_or_different_lengths?(comparison)
    comparison == body || comparison.length != body.length
  end

  def comparison_matches?(comparison)
    return false if dups_or_different_lengths?(comparison)
    comparison_chars = to_char_sorted_array(comparison)
    body_chars = to_char_sorted_array(body)
    comparison_chars == body_chars
  end

  def to_char_sorted_array(word)
    word.chars.sort
  end
end
