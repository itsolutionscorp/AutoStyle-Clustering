class Anagram

  def initialize(base_word)
    @base_word = base_word.downcase
  end

  def match(words)
    words.select { |w| anagram?(w.downcase) && !same?(w.downcase) }
  end

  private

  def anagram?(word)
    @base_word.chars.sort == word.chars.sort
  end

  def same?(word)
    @base_word == word
  end

end
