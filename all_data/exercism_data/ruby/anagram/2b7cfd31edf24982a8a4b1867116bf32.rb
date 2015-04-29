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
    case true
    when identical?(input), different_length?(input), different_characters?(input), different_character_occurances?(input)
      return false
    else
      return true
    end
  end

  def identical?(input)
    input.eql?(@lowercase_word)
  end

  def different_length?(input)
    !input.length.eql?(@word.length)
  end

  def different_characters?(input)
    input.each_char.count { |character| !@lowercase_word.include?(character) } > 0
  end

  def different_character_occurances?(input)
    !input.chars.sort.eql?(@lowercase_word.chars.sort)
  end
end
