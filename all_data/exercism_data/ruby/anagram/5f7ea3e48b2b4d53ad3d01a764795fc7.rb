class Anagram
  attr_reader :word
  def initialize(input_word)
    @word = Word.new(input_word)
  end

  def match(input_words)
    candidates = input_words.map { |input_word| Word.new(input_word) }
    candidates.select { |candidate| candidate.anagram? word }
  end

  class Word < String
    def anagram?(other)
      split('').sort == other.split('').sort
    end
  end
end
