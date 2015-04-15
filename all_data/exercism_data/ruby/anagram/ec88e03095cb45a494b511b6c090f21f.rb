class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(candidates_words)
    candidates_words.select { |word| anagram?(word.downcase) }
  end

  private
    def anagram?(word)
      (word != @word) && (word.chars.sort == @word.chars.sort)
    end
end
