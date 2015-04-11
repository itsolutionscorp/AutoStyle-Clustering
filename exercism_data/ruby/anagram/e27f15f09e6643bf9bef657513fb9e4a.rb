class Anagram
  def initialize(word)
    @word = word.downcase
    @sorted_symbols = @word.chars.sort
  end

  def match(candidates_words)
    candidates_words.select { |word| anagram?(word.downcase) }
  end

  private
    def anagram?(word)
      (word != @word) && (word.chars.sort == @sorted_symbols)
    end
end
