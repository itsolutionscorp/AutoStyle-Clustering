class Anagram
  class Word
    def initialize(word)
      @word = word
    end

    def anagram?(other_word)
      @word.downcase.each_char.sort == other_word.downcase.each_char.sort
    end
  end

  def initialize(word)
    @word = Word.new(word)
  end

  def match(candidates)
    candidates.select { |candidate| @word.anagram?(candidate) }
  end
end
