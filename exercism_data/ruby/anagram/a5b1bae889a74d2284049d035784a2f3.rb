class Anagram

  attr_accessor :word

  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.select { |w| permutations.include? w.downcase unless w.downcase == word}
  end

  def permutations
    word_letters.permutation.to_a.collect { |letters| letters.join }
  end

  def word_letters
    word.split('')
  end
    
end
