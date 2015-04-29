class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select{ |word| anagram?(word) }
  end

  private
  def anagram?(word)
    !same_word?(word) && same_length?(word) && same_letters?(word)
  end

  def same_letters?(word)
    word.downcase.split('').sort == @word.downcase.split('').sort
  end

  def same_length?(word)
    word.length == @word.length
  end

  def same_word?(word)
    word.downcase == @word.downcase
  end
end
