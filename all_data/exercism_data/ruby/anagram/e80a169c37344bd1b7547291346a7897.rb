class Anagram < Struct.new(:anagram)
  def match(words)
    words.select {|word| different_word?(word) && anagram?(word) }
  end

  def different_word?(word)
    word.downcase != anagram.downcase
  end

  def anagram?(word)
    word.downcase.chars.sort == anagram.downcase.chars.sort
  end
end
