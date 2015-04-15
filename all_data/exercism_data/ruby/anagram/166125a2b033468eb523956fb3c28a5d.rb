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

    available_chars = self.downcase.chars.to_a
    word.each_char do |char|
      unless index = available_chars.index(char)
        return false
      end
      available_chars.delete_at(index)
    end

    true
  end

end
