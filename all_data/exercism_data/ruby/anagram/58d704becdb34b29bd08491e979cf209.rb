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
    def letter_count
      split('').reduce(Hash.new(0)) { |counts, letter| counts[letter] = counts[letter] + 1; counts }
    end

    def anagram?(other)
      self.letter_count == other.letter_count
    end
  end
end
