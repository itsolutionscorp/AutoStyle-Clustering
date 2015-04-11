class Anagram
  def initialize(word)
    self.original_word   = word
    self.normalized_word = normalize word
  end

  def match(potential_anagrams)
    potential_anagrams.select do |potential|
      potential.downcase != original_word.downcase &&
        normalize(potential) == normalized_word
    end
  end

  private

  attr_accessor :original_word, :normalized_word

  def normalize(string)
    string.downcase.each_char.to_a.sort
  end
end
