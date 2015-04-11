class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.select { |w| anagram?(w.downcase) }
  end

  def anagram?(word)
    return false if @word == word

    @word.alphagram == word.alphagram
  end
end

class String
  def alphagram
    self.chars.sort
  end
end
