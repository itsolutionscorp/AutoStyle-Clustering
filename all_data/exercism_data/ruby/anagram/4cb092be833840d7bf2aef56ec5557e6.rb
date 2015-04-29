class Anagram
  def initialize(word)
    @word = word
    @normalized = normalize(word.downcase)
  end

  def match(list)
    list.select{ |word| anagram?(word) and not identical?(word) }
  end

  def normalize(word)
    word.downcase.chars.sort.join
  end

  def anagram?(word)
    normalize(word) == @normalized
  end

  def identical?(word)
    word.downcase == @word.downcase
  end

end
