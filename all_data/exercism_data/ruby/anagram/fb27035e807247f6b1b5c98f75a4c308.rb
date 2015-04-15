class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(options)
    sorted_word = sort_word(word)
  
    options.select do |option|
      next if word == option.downcase
      sorted_option = sort_word(option)
      option if sorted_word == sorted_option
    end
  end

  def sort_word(input)
    input.downcase.chars.sort
  end
end
