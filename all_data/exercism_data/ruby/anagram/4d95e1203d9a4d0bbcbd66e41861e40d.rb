class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select {|candidate|
      words_are_anagram?(word, candidate)
    }
  end

  private

  def words_are_anagram?(word_a, word_b)
    make_string_comparable(word_a) == make_string_comparable(word_b)
  end

  def make_string_comparable(string)
    string.split("").inject(Hash.new(0)) {|counts, letter|
      counts[letter] += 1
      counts
    }
  end
end
