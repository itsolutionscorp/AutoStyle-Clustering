class Anagram
  
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select { |current_word|  anagram?(current_word) }
  end

  private

    attr_reader :word

    def standarized_sorted_word
      WordsTransformer.new(word).standarized_sorted_word
    end

    def anagram?(other_word)
      other_word != word && WordsTransformer.new(other_word).sorted_word == standarized_sorted_word 
    end
end


class WordsTransformer
  
  attr_reader :word
  def initialize(word)
    @word = word
  end

  def standarized_sorted_word
    sort(word.downcase)
  end

  def sorted_word
    sort(word)
  end

    private

      def sort(current_word)
        current_word.chars.sort.join
      end

end
