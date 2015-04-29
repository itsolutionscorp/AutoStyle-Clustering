class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  def anagram?(word)
    word != @word && word.chars.sort.join == @word.downcase.chars.sort.join
  end
end
