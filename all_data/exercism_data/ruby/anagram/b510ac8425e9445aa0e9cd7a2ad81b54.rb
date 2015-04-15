class Anagram
  attr_accessor :word

  def initialize word
    self.word = word
  end

  def match words
    words.map{ |word| word if self.word.downcase.chars.permutation.include? word.downcase.chars }.compact.reject{ |word| word.downcase == self.word.downcase}
  end
end
