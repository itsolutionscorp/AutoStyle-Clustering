class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.select { |w|
      down_w = w.downcase # I hate temp variables
      w.size == @word.size &&
        down_w.downcase != @word &&
        down_w.downcase.chars.sort == @word.chars.sort
    }
  end
end
