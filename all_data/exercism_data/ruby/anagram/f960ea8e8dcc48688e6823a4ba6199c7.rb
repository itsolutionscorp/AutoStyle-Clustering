class Anagram

  def initialize(word)
    @original = word.downcase
    @alphagram = alphagram word
  end

  def match(words)
    words.select { |word| distinct_anagram? word }
  end

  private

  def distinct_anagram?(word)
    word.downcase != @original && alphagram(word) == @alphagram
  end

  def alphagram(word)
    word.downcase.chars.sort
  end

end
