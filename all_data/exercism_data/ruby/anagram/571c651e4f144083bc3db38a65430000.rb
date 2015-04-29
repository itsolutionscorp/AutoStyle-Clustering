class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  private
  def anagram?(word)
    word != @word && sort_chars(word) == sort_chars(@word.downcase)
  end

  def sort_chars(word)
    word.chars.sort
  end
end
