class Anagram < Struct.new(:anagram)
  def match(words)
    words.select {|word| anagram?(word) }
  end

  def anagram?(word)
    word.downcase != anagram.downcase && word.downcase.chars.sort == anagram.downcase.chars.sort
  end
end
