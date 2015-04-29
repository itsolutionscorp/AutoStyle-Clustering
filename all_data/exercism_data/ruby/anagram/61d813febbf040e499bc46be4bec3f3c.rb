class Anagram
  def initialize(word)
    @base_word = word
  end

  def match(words)
    words.select { |word| anagram?(@base_word, word) }
  end

  private

  def anagram?(base, candidate)
    a, b = base.downcase, candidate.downcase
    a != b && a.chars.sort == b.chars.sort
  end
end
