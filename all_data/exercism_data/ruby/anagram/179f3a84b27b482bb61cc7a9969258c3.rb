class String
  def anagram?(word)
    return false if word.downcase == self.downcase
    return self.downcase.to_sorted_a == word.downcase.to_sorted_a    
  end

  def to_sorted_a
    self.split('').sort  
  end    
end

class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select { |x| @word.anagram?(x) }
  end
end
