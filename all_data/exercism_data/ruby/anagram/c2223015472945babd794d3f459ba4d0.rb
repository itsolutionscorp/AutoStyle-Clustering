class Anagram
  def initialize(word)
    @word = word.downcase
    @words_sorted_symbols = sorted_symbols(@word)
  end

  def match(candidates_words)
    candidates_words.select { |word| anagram?(word.downcase) }
  end

  private
    def sorted_symbols(word)
      word.chars.sort
    end

    def anagram?(word)
      (word != @word) && (sorted_symbols(word) == @words_sorted_symbols)
    end
end
