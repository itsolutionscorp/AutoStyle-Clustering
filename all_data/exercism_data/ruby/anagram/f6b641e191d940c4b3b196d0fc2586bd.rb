class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(candidates_words)
    candidates_words.select do |word|
      candidate_word = Word.new(word)
      @word.anagram?(candidate_word)
    end
  end

  private
    class Word
      attr_reader :word
      attr_reader :sorted_symbols

      def initialize(word)
        @word = word.downcase
        @sorted_symbols = @word.chars.sort
      end

      def anagram?(other)
        (@word != other.word) && (@sorted_symbols == other.sorted_symbols)
      end
    end
end
