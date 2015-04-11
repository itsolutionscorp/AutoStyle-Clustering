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
    unidentical?(input) && same_length?(input) && same_characters?(input) && same_alphagram?(input)
  end

  def unidentical?(input)
    !input.eql?(@lowercase_word)
  end

  def same_length?(input)
    input.length.eql?(@word.length)
  end

  def same_characters?(input)
    input.each_char.count { |character| !@lowercase_word.include?(character) }.zero?
  end

  def same_alphagram?(input)
    input.chars.sort.eql?(@lowercase_word.chars.sort)
  end
end
