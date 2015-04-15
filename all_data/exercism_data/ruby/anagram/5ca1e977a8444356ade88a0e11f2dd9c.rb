class Anagram
  attr_accessor :word

  def initialize word
    self.word = word
  end

  def match words
    words.map do |word|
      word if (word != self.word) && (self.word.downcase.chars.sort == word.downcase.chars.sort)
    end.compact
  end
end
