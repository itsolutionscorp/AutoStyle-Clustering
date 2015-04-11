class Anagram
  def initialize(word)
    @word = word
  end

  def match(input)
    input.each_with_object([]) { |word, anagrams| anagrams << word if is_anagram?(word.downcase) }
  end

  private

  def is_anagram?(input)
    if is_identical?(input)
      return false
    elsif is_of_different_length?(input)
      return false
    elsif is_composed_of_different_characters?(input)
      return false
    elsif is_of_different_character_occurances?(input)
      return false
    else
      return true
    end
  end

  def is_identical?(input)
    input.downcase.eql?(@word.downcase)
  end

  def is_of_different_length?(input)
    !input.length.eql?(@word.length)
  end

  def is_composed_of_different_characters?(input)
    counter = 0
    input.each_char do |character|
      counter += 1 if !@word.downcase.include?(character)
    end
    counter > 0
  end

  def is_of_different_character_occurances?(input)
    !input.chars.sort.eql?(@word.downcase.chars.sort)
  end
end
