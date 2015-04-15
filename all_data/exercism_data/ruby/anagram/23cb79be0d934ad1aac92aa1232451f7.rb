class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select {|c|
      letter_counts(c) == letter_counts(word)
    }
  end

  private

  def letter_counts(string)
    string.split("").inject(Hash.new(0)) {|counts, letter|
      counts[letter] += 1
      counts
    }
  end
end
