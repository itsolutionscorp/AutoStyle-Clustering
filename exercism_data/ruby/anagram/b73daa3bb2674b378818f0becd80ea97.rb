class Anagram

  def initialize(word)
    @word = word
  end

  def match(possibilities)
    possibilities.select do |anagram|
      @word.is_anagram?(anagram)
    end
  end

end

class String

  def is_anagram?(word)
    return false if self.casecmp(word) == 0
    self.downcase.sorted == word.downcase.sorted
  end

  def sorted
    self.chars.sort.join    
  end

end
