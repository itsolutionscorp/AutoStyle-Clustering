class String
  def anagram?(word)
    return false if word.downcase == self.downcase
    word_breakdown = ->(str) { str.chars.sort }
    return word_breakdown.(self.downcase) == word_breakdown.(word.downcase)    
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
