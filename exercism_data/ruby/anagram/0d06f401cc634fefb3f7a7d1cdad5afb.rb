class Anagram
  attr_reader :word
  def initialize(input_word)
    @word = Word.new(input_word)
  end

  def match(input_words)
    candidates = input_words.map { |input_word| Word.new(input_word) }
    candidates.select { |candidate| anagram?(candidate) }
  end

  def anagram?(candidate)
    candidate.letter_count == word.letter_count
  end

  class Word < String
    def letter_count
      split('').reduce(Hash.new(0)) { |counts, letter| counts[letter] = counts[letter] + 1; counts }
    end
  end
end
