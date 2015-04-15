class Anagram
  def initialize(input)
    @input = input
  end

  def match(matchers=[])
    matchers.collect { |word| word if anagram?(word) }.compact
  end

  def anagram?(word)
    @input.downcase != word.downcase && characters(@input) == characters(word)
  end

  def characters(word)
    word.downcase.each_char.sort
  end
end
