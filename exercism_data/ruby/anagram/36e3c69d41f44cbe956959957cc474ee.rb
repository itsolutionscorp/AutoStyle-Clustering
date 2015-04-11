class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(candidates)
    candidates.select {|candidate|
      words_are_anagram?(subject, candidate)
    }
  end

  private

  def words_are_anagram?(word_a, word_b)
    fingerprint(word_a) == fingerprint(word_b)
  end

  def fingerprint(string)
    string.chars.each_with_object(Hash.new(0)) {|letter, counts|
      counts[letter] += 1
    }
  end
end
