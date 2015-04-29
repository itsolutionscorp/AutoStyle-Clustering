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
    word = word.downcase
    return false if self == word
    return false if self.length != word.length

    self.downcase.sorted == word.sorted
  end

  def sorted
    self.chars.sort.join    
  end

end
