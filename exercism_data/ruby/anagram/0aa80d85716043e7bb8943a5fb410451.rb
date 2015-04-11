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
    not_anagram?(input)
  end

  def not_anagram?(input)
    identical?(input) && different_length?(input) && different_characters?(input) && different_alphagram?(input)
  end

  def identical?(input)
    !input.eql?(@lowercase_word)
  end

  def different_length?(input)
    input.length.eql?(@word.length)
  end

  def different_characters?(input)
    input.each_char.count { |character| !@lowercase_word.include?(character) }.zero?
  end

  def different_alphagram?(input)
    input.chars.sort.eql?(@lowercase_word.chars.sort)
  end
end
