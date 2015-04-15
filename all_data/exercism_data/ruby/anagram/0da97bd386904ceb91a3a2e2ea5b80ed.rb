class Anagram
  def initialize(word)
    @word = word
    @lowercase_word = word.downcase
  end

  def match(input)
    input.each_with_object([]) { |word, anagrams| anagrams << word if anagram?(word.downcase) }
  end

  private

  def anagram?(input)
    unidentical?(input) && eql_alphagram?(input)
  end

  def unidentical?(input)
    !input.eql?(@lowercase_word)
  end

  def eql_alphagram?(input)
    input.chars.sort.eql?(@lowercase_word.chars.sort)
  end
end
