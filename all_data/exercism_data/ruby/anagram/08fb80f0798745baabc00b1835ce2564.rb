class Anagram < Struct.new(:initial_word)
  def match words
    words.select do |word|
      self.class.anagrams?(initial_word.downcase, word.downcase)
    end
  end

  def self.anagrams? a, b
    a != b and a.chars.sort == b.chars.sort
  end
end
