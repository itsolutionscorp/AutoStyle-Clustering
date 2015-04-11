class Anagram
  attr_reader :word
  def initialize(word)
    @word = word
  end

  def match(dictionary)
    dictionary.is_a?(Array) && word.is_a?(String) ? fix_case(dictionary) - (dictionary - new_dictionary) : []
  end

  private

  attr_reader :new_dictionary

  def fix_case(dictionary)
    if (word == word.downcase)
      @new_dictionary = word.chars.permutation.to_a.inject([]){|r,i| r << i.join.downcase}
      dictionary = dictionary - [word.downcase]
    elsif (word == word.upcase)
      @new_dictionary = word.chars.permutation.to_a.inject([]){|r,i| r << i.join.upcase}
      dictionary = dictionary - [word.upcase]
    elsif (word == word.capitalize)
      @new_dictionary = word.chars.permutation.to_a.inject([]){|r,i| r << i.join.capitalize}
      dictionary = dictionary - [word.capitalize]
    end
    dictionary
  end
end
