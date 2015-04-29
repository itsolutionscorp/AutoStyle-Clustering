class Anagram
  def initialize(string)
    @word = Word.new(string)
  end

  def match(anagram_candidates)
    anagram_candidates.select { |candidate| anagram?(Word.new(candidate)) }
  end

  private
    def anagram?(word)
      word != @word && word.alphagram == @word.alphagram
    end
end

class Word
  attr_reader :downcase, :alphagram

  def initialize(string)
    @downcase = string.downcase
    @alphagram = @downcase.chars.sort
  end

  def ==(word)
    self.downcase == word.downcase
  end
end
